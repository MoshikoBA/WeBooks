package com.mba.views

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mba.utils.MotionDetector
import com.mba.utils.ViewConsts
import com.mba.utils.ViewMeasureUtils

open class MyRecyclerView : RecyclerView {
    private var pagingScrollListener: OnScrollListener? = null
    private val motionDetector = MotionDetector(ViewConsts.dp(2))
    private var shouldDisableScrollingInPager = false

    constructor(context: Context?) : super(context!!) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context!!, attrs, defStyle) {
        init()
    }

    private fun init() {
        isMotionEventSplittingEnabled = false

        // Added this to fix an issue where a horizontal
        isFocusable = false
        isFocusableInTouchMode = false
    }

    /**
     * We want to allow child HorizontalSectionLayouts to have a more forgiving horizontal scrolling behaviour
     * In order to achieve this we detect horizontal scroll movements with out motion detector and a custom slope slope of 2dp
     * The Framework's RecyclerView's slope is 8dp.
     * By doing so, we are making sure that we are not "stealing" any horizontal scrolling movements from a scrolling child view
     *
     * @param ev - motionEvent
     * @return
     */
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {

        // we feed the event to our motion detector so it can update it's internal scrolling state
        motionDetector.onInterceptTouchEvent(ev)
        if (ev.action == MotionEvent.ACTION_MOVE) {
            if (layoutManager != null) {
                val canScrollVertically = layoutManager!!.canScrollVertically()
                if (canScrollVertically) {
                    if (motionDetector.isHorizontalScroll) {
                        for (i in 0 until childCount) {
                            if (ViewMeasureUtils.isPointInsideView(ev.x, ev.y, getChildAt(i))) {
                                return false
                            }
                        }
                    }
                }
            }
        }
        return super.onInterceptTouchEvent(ev)
    }

    fun findLastReasonableVisibleItemPosition(ratio: Float): Int {
        for (i in childCount - 1 downTo -1 + 1) {
            val view = getChildAt(i)
            if (ViewMeasureUtils.isViewReasonablyVisible(view, this, ratio)) {
                return getChildAdapterPosition(view)
            }
        }
        return NO_POSITION
    }

    fun findFirstReasonablyVisibleItemPosition(ratio: Float): Int {
        for (i in 0 until childCount) {
            val view = getChildAt(i)
            if (ViewMeasureUtils.isViewReasonablyVisible(view, this, ratio)) {
                return getChildAdapterPosition(view)
            }
        }
        return NO_POSITION
    }

    fun getViewAtIndex(index: Int): View? {
        if (index >= 0) {
            for (i in 0 until childCount) {
                val child = getChildAt(i)
                val pos = getChildAdapterPosition(child)
                if (pos == index) {
                    return child
                }
            }
        }

        // There is no view for this index - it is offscreen
        return null
    }

    fun setNoOverscroll() {
        overScrollMode = View.OVER_SCROLL_NEVER
    }

    fun <A : Adapter<*>?> getAdapterCast(): A? {
        return adapter as? A?
    }

    fun canScroll(): Boolean {
        return shouldDisableScrollingInPager
    }

    fun setShouldDisableScrollingInPager(shouldDisableScrollingInPager: Boolean) {
        this.shouldDisableScrollingInPager = shouldDisableScrollingInPager
    }

    /*
This function enabling paging on any RecyclerView, the trigger for the check is onScrollStateChanged
function of RecyclerView.OnScrollListener. Each time we check last full visible item on the screen and
verify that we have at least half page loaded more, if not we requesting new page via loadMoreItemsCallback
function that we received in the constructor.
 */

    fun setupPaging(
            pageSize: Int,
            swipeRefreshLayout: SwipeRefreshLayout? = null,
            allowPagingPreviousPages: Boolean = false,
            countOfHeaders: Int = 0,
            onSwipedToRefresh: (() -> Unit)? = null,
            loadMoreItemsCallback: (offset: Int, pageSize: Int) -> Unit) {

        // map for checking if we already requested the page
        val requestedPreviousPagesMap = hashMapOf<Int, Boolean>()
        val requestedNextPagesMap = hashMapOf<Int, Boolean>()

        // in the case the paging working with swipe to refresh
        swipeRefreshLayout?.let { safeSwipeRefreshLayout ->
            safeSwipeRefreshLayout.setOnRefreshListener {
                requestedPreviousPagesMap.clear()
                requestedPreviousPagesMap[0] = true

                requestedNextPagesMap.clear()
                requestedNextPagesMap[0] = true
                onSwipedToRefresh?.invoke()
                loadMoreItemsCallback(0, pageSize)
            }
        }

        // clear old listeners
        pagingScrollListener?.let { removeOnScrollListener(it) }

        pagingScrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy != 0) {
                    val isScrollingUp = dy < 0

                    layoutManager?.let { layoutManager ->
                        if (layoutManager is LinearLayoutManager) {

                            // enable swipe only if we are on the top
                            swipeRefreshLayout?.let { safeSwipeRefreshLayout ->
                                val enabled = layoutManager.findFirstCompletelyVisibleItemPosition() == 0
                                safeSwipeRefreshLayout.isEnabled = enabled
                            }

                            synchronized(this) {
                                val requestedPagesMap = if (isScrollingUp) requestedPreviousPagesMap else requestedNextPagesMap
                                val itemCount = layoutManager.itemCount - countOfHeaders

                                if (requestedPagesMap[itemCount] == null) {
                                    if (isNeedToLoadMore(layoutManager, pageSize, isScrollingUp)) {
                                        requestedPagesMap[itemCount] = true
                                        if (isScrollingUp && allowPagingPreviousPages) {
                                            loadMoreItemsCallback(-pageSize, pageSize)
                                        } else {
                                            loadMoreItemsCallback(itemCount, pageSize)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            fun isNeedToLoadMore(layoutManager: LinearLayoutManager, pageSize: Int, isScrollingUp: Boolean): Boolean {
                return if (isScrollingUp) {
                    //Need to load previous Page
                    val currentItem = layoutManager.findFirstVisibleItemPosition()
                    currentItem < pageSize / 2
                } else {
                    val currentItem = layoutManager.findLastVisibleItemPosition()
                    val itemCount = layoutManager.itemCount
                    currentItem > itemCount - pageSize / 2
                }
            }
        }
        addOnScrollListener(pagingScrollListener as OnScrollListener)
        requestedNextPagesMap[0] = true
        loadMoreItemsCallback(0, pageSize)
    }
}