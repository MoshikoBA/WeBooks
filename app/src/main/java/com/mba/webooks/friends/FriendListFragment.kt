package com.mba.webooks.friends

import android.graphics.Rect
import android.os.Handler
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.mba.basescreens.AbstractViewModelFragment
import com.mba.basescreens.UiState
import com.mba.extensions.setVertical
import com.mba.utils.ViewConsts
import com.mba.views.EmptyStateConfig
import com.mba.webooks.R
import kotlinx.android.synthetic.main.layout_recycler_with_fab.*

class FriendListFragment : AbstractViewModelFragment<FriendListFragmentViewModel>() {
    val adapter = FriendListFragmentAdapter()

    override fun initializeViewModel() {
        viewModel.uiStatLiveData.observe(this, Observer {
            when(it) {
                UiState.Loading -> recyclerViewHolder.showLoading()
                UiState.Success -> recyclerViewHolder.showSuccess()
            }
        })

        viewModel.friendsLiveData.observe(this, Observer {
            adapter.items = it
            recyclerViewHolder.showOrHideEmptyState(it.isEmpty())
        })
    }

    override val contentViewLayoutResId: Int = R.layout.layout_recycler_with_fab

    override fun getName(): String = "Friends"

    override fun enableSwipeToRefresh() = true

    override fun initUi() {
        initRecyclerView()
        viewModel.loadData()
    }

    override fun onSwipeToRefresh() {
        super.onSwipeToRefresh()
        viewModel.loadData()
    }

    private fun initRecyclerView() {
        recyclerViewHolder.emptyStateConfig = EmptyStateConfig(title = getString(R.string.no_books_yet), subtitle = getString(R.string.tap_the_plus_to_add_your_first_book), imageRes =  R.drawable.ic_books_empty_state)
        recyclerViewHolder.recyclerView.setVertical(context)
        recyclerViewHolder.recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
                outRect.left = ViewConsts.dp(16)
                outRect.right = ViewConsts.dp(16)
                outRect.top = ViewConsts.dp(16)

                if (itemPosition == adapter.itemCount - 1) {
                    outRect.bottom = ViewConsts.dp(16)
                }
            }
        })

        recyclerViewHolder.recyclerView.adapter = adapter
    }
}