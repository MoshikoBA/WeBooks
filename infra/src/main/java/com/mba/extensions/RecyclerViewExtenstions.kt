package com.mba.extensions

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setVertical(context: Context) {
    layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false).apply {
        isItemPrefetchEnabled = false
    }
}