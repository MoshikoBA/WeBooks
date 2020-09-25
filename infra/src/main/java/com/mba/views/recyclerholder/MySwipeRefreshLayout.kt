package com.mba.views.recyclerholder

import android.content.Context
import android.util.AttributeSet
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class MySwipeRefreshLayout : SwipeRefreshLayout {
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context) : super(context) {}

    private val callbacks = arrayListOf<SwipeRefreshLayout.OnRefreshListener?>()

    override fun setOnRefreshListener(listener: OnRefreshListener?) {
        super.setOnRefreshListener(listener)
        callbacks.add(listener)
    }

    fun refresh() {
        callbacks.forEach {
            it?.run {
                onRefresh()
            }
        }
    }

    fun disable() {
        setOnRefreshListener {
            isRefreshing = false
        }
    }
}