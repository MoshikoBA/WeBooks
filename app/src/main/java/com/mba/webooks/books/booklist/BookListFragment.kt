package com.mba.webooks.books.booklist

import android.os.Handler
import androidx.lifecycle.Observer
import com.mba.basescreens.AbstractViewModelFragment
import com.mba.extensions.setVertical
import com.mba.views.EmptyStateConfig
import com.mba.webooks.R
import kotlinx.android.synthetic.main.layout_recycler_with_fab.*

class BookListFragment : AbstractViewModelFragment<BookListFragmentViewModel>() {
    val adapter = BookListFragmentAdapter()

    override fun initializeViewModel() {
        viewModel.booksLiveData.observe(this, Observer {
            adapter.items = it
        })
    }

    override val contentViewLayoutResId: Int = R.layout.layout_recycler_with_fab

    override fun getName(): String = "Books"

    override fun enableSwipeToRefresh() = true

    override fun initUi() {
        recyclerViewHolder.emptyStateConfig = EmptyStateConfig(title = getString(R.string.no_books_yet), imageRes =  R.drawable.ic_empty_state_categories)
        recyclerViewHolder.recyclerView.setVertical(context)
        recyclerViewHolder.recyclerView.adapter = adapter
        Handler().postDelayed({viewModel.loadData()}, 1000L)
    }
}