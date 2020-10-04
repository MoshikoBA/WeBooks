package com.mba.webooks

import android.os.Bundle
import com.mba.Core
import com.mba.basescreens.BaseActivity
import com.mba.utils.ScreenUtils
import com.mba.webooks.loadingscreen.LoadingFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, LoadingFragment())
//                .commitNow()
//        }

        Core.context = baseContext
        ScreenUtils.openScreen(LoadingFragment::class.java, this)
    }
}