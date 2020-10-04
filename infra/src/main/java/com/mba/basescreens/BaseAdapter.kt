package com.mba.basescreens

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<T> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = items.size
}