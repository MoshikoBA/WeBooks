package com.mba.webooks.books.booklist

import com.mba.basescreens.AbstractViewModelFragment
import com.mba.webooks.R

class BookListFragment : AbstractViewModelFragment<BookListFragmentViewModel>() {
    override fun initializeViewModel() {

    }

    override val contentViewLayoutResId: Int = R.layout.book_list_fragment

    override fun getName(): String = "Books"

    override fun enableSwipeToRefresh() = true

    override fun initUi() {

    }
}