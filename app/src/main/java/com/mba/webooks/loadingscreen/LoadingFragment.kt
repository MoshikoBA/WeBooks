package com.mba.webooks.loadingscreen

import android.os.Handler
import com.mba.basescreens.AbstractViewModelFragment
import com.mba.utils.ScreenUtils
import com.mba.webooks.R
import com.mba.webooks.books.booklist.BookListFragment
import com.mba.webooks.friends.FriendListFragment
import kotlinx.android.synthetic.main.loading_fragment.*

class LoadingFragment : AbstractViewModelFragment<LoadingFragmentViewModel>() {


    override val contentViewLayoutResId: Int
        get() = R.layout.loading_fragment

    override fun getName() = "Loading Screen"

    override fun enableSwipeToRefresh() = false

    override fun initUi() {
        myBooks.setOnClickListener {
            ScreenUtils.openScreen(BookListFragment::class.java, baseActivity, false)
        }

        myFriends.setOnClickListener {
            ScreenUtils.openScreen(FriendListFragment::class.java, baseActivity, false)
        }

//         Handler().postDelayed({
//            ScreenUtils.openScreen(BookListFragment::class.java, baseActivity, true)
//         }, 1000)
    }

    override fun initializeViewModel() {

    }
}