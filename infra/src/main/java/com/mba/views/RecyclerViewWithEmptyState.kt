package com.mba.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.mba.extensions.R
import com.mba.extensions.showOrHide
import kotlinx.android.synthetic.main.layout_recycler_holder.view.*

class RecyclerViewWithEmptyState: FrameLayout {

    val recyclerView: RecyclerView
        get() = recycler

    var emptyStateConfig: EmptyStateConfig? = null

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
        LayoutInflater.from(context).inflate(R.layout.layout_recycler_holder, this)
    }

    fun showOrHideEmptyState(show: Boolean) {
        emptyState.showOrHide(show)
    }
}