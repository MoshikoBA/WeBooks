package com.mba.webooks.loadingscreen

import android.os.Handler
import com.mba.basescreens.AbstractViewModelFragment
import com.mba.utils.ScreenUtils
import com.mba.webooks.R
import com.mba.webooks.books.booklist.BookListFragment

class LoadingFragment : AbstractViewModelFragment<LoadingFragmentViewModel>() {


    override val contentViewLayoutResId: Int
        get() = R.layout.loading_fragment

    override fun getName() = "Loading Screen"

    override fun enableSwipeToRefresh() = false

    override fun initUi() {
         Handler().postDelayed({
            ScreenUtils.openScreen(BookListFragment::class.java, baseActivity, true)
         }, 2000)
    }

    override fun initializeViewModel() {

    }
}