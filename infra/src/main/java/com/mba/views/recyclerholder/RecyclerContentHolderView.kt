package com.mba.views.recyclerholder


import android.app.Service
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mba.extensions.*
import com.mba.utils.ViewConsts

class RecyclerContentHolderView : FrameLayout {

//    private var supportPaging = false
//    private var disableSwipeToRefresh = false
//    private var customMsg: View? = null
//    private var errorView: View? = null
//    private var errorTitle: AppCompatTextView? = null
//    private var errorSubTitle: AppCompatTextView? = null
//    private var errorImage: ImageView? = null
//    private var onScrolledNotifier: OnScrolledNotifier? = null
//
//    var isDuringPaging = true
//        set(value) {
//            if (value) {
//                recyclerView?.setPaddingForSides(bottom = ViewConsts.dp(64))
//                if (isScrolledAllTheWayDown()) {
//                    pagingProgress?.show()
//                }
//            } else {
//                pagingProgress?.gone()
//                recyclerView?.setPaddingForSides(bottom = 0)
//            }
//
//
//            field = value
//        }
//
//    var recyclerView: MyRecyclerView? = null
//    var swipeToRefresh: MySwipeRefreshLayout? = null
//    var progress: ProgressBar? = null
//    var pagingProgress: ProgressBar? = null
//    var isLoading = true
//
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
//
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}
//
//        val attributes: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.RecyclerContentHolderViewAttrs, defStyleAttr, 0)
//
//        disableSwipeToRefresh = attributes.getBoolean(R.styleable.RecyclerContentHolderViewAttrs_disableSwipeToRefresh, true)
//        supportPaging = attributes.getBoolean(R.styleable.RecyclerContentHolderViewAttrs_supportPaging, false);
//
//        attributes.recycle()
//
//        initialize()
//    }
//
//    private fun initialize() {
//
//        var viewGroup = this as ViewGroup
//        if (!disableSwipeToRefresh) {
//            swipeToRefresh = MySwipeRefreshLayout(context).also {
//                addView(it)
//                viewGroup = it
//            }
//        }
//
//        recyclerView = MyRecyclerView(context).also {
//            it.setVertical(context)
//            it.isNestedScrollingEnabled = false
//            it.clipToPadding = false
//            viewGroup.addView(it)
//        }
//        if (supportPaging) {
//            supportPagingIndicator()
//        }
//
//        progress = ProgressBar(context)
//        progress?.isIndeterminate = true
//        progress?.indeterminateDrawable = ContextCompat.getDrawable(context, R.drawable.ivy_progress)
//        val progressParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.CENTER)
//        addView(progress, progressParams)
//        progress?.layoutParams = progressParams
//        errorView = (context.getSystemService(Service.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(R.layout.layout_no_internet_connection, null).apply {
//            errorTitle = findViewById(R.id.title)
//            errorSubTitle = findViewById(R.id.subtitle)
//            errorImage = findViewById(R.id.image)
//            gone()
//        }
//        addView(errorView)
//
//        requestLayout()
//    }
//
//    fun autoHideFabWhenScrolledAllTheWay(fab: View) {
//        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                if (dy < 0 && fab.isNotVisible()){
//                    fab.showAnimated()
//                } else if (isDuringPaging && isScrolledAllTheWayDown()) {
//                    fab.goneAnimated()
//                }
//            }
//        })
//    }
//
//    fun supportPagingIndicator() {
//        supportPaging = true
//        recyclerView.let {
//            it.clipChildren = false
//            it.setPaddingForSides(bottom = ViewConsts.dp(64))
//            pagingProgress = ProgressBar(context)
//            val layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL)
//            layoutParams.bottomMargin = ViewConsts.dp(16)
//            pagingProgress!!.layoutParams = layoutParams
//            pagingProgress!!.gone()
//            addView(pagingProgress)
//            it.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                    super.onScrolled(recyclerView, dx, dy)
//                    if (isScrolledAllTheWayDown()) {
//                        if (isDuringPaging) {
//                            pagingProgress?.show()
//                        } else
//                            pagingProgress?.gone()
//                    } else {
//                        pagingProgress?.gone()
//                    }
//                }
//            })
//        }
//    }
//
//    fun setTopPaddingToSwipeToRefresh(padding: Int) =
//            swipeToRefresh?.run {
//                val moovingSize = progressViewEndOffset - progressViewStartOffset
//                setProgressViewOffset(false, 0, padding + moovingSize)
//            }
//
//    fun showSuccess() {
//        isLoading = false
//        customMsg?.gone()
//        recyclerView?.show()
//        progress?.gone()
//        errorView?.gone()
//        pagingProgress?.gone()
//        swipeToRefresh?.isRefreshing = false
//    }
//
//    fun showLoading() {
//        isLoading = true
//        progress?.show()
//        customMsg?.gone()
//        recyclerView?.gone()
//        errorView?.gone()
//        pagingProgress?.gone()
//        if (supportPaging) {
//            recyclerView?.setPaddingForSides(bottom = ViewConsts.dp(64))
//        }
//    }
//
//    fun setCustomMsgView(view: View) {
//        if (customMsg != null) {
//            removeView(customMsg)
//        }
//        this.customMsg = view
//        addView(customMsg, LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, Gravity.CENTER))
//        customMsg?.gone()
//    }
//
//    fun showCustomMsgView(haveHeader: Boolean = false) {
//        isLoading = false
//        recyclerView?.showOrGone(haveHeader)
//        progress?.gone()
//        customMsg?.show()
//        swipeToRefresh?.isRefreshing = false
//        pagingProgress?.gone()
//    }
//
//    fun showError() {
//        isLoading = false
//        swipeToRefresh?.isRefreshing = false
//        if (!isOnline(context)) {
//            initErrorForNoInternetConnection()
//        } else {
//            initErrorForGeneralError()
//        }
//        errorView?.show()
//        progress?.gone()
//        pagingProgress?.gone()
//        recyclerView?.gone()
//        customMsg?.gone()
//    }
//
//    private fun initErrorForNoInternetConnection() {
//        errorImage?.show()
//        errorTitle?.text = context.getString(R.string.you_are_offline)
//        errorSubTitle?.text = context.getString(R.string.please_connect_to_the_internet_and_try_again)
//        errorSubTitle?.show()
//    }
//
//    private fun initErrorForGeneralError() {
//        errorImage?.gone()
//        errorTitle?.text = context.getString(R.string.an_error_has_occurred)
//        errorSubTitle?.gone()
//    }
//
//    fun setHorizontal() {
//        recyclerView?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//    }
//
//    fun isScrolledAllTheWayUp(): Boolean {
//        recyclerView?.adapter?.let {
//            return isCompletelyVisibleOnTop(0)
//        } ?: run {
//            return false
//        }
//    }
//
//    fun isScrolledAllTheWayDown(): Boolean {
//        recyclerView?.adapter?.let {
//            return isCompletelyVisibleOnBottom(it.itemCount - 1)
//        } ?: run {
//            return false
//        }
//    }
//
//    fun isCompletelyVisibleOnTop(itemIndex: Int): Boolean {
//        recyclerView?.let {
//            return ((it.layoutManager) as? LinearLayoutManager)?.findFirstCompletelyVisibleItemPosition() == itemIndex
//        } ?: run {
//            return false
//        }
//    }
//
//    fun isCompletelyVisibleOnBottom(itemIndex: Int): Boolean {
//        recyclerView?.let {
//            return ((it.layoutManager) as? LinearLayoutManager)?.findLastCompletelyVisibleItemPosition() == itemIndex
//        } ?: run {
//            return false
//        }
//    }
//
//
//    fun setupEmptyState(emptyStateConfig: MessageConfig) {
//        setCustomMsgView(EmptyStateView(context, emptyStateConfig))
//    }
//
//    fun setupScrollingToolbar(scrollingToolbarInterface: ScrollingToolbarTabsHostInterface){
//        onScrolledNotifier?.let {
//            recyclerView?.removeOnScrollListener(it)
//        }
//
//        OnScrolledNotifier(scrollingToolbarInterface).apply {
//            recyclerView?.addOnScrollListener(this)
//            onScrolledNotifier = this
//        }
//    }
//
//    fun stopScrollingToolbar() {
//        onScrolledNotifier?.let {
//            recyclerView?.removeOnScrollListener(it)
//        }
//    }
}
