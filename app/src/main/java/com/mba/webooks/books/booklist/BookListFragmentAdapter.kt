package com.mba.webooks.books.booklist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mba.basescreens.BaseAdapter
import com.mba.data.BookViewModel
import com.mba.webooks.R

class BookListFragmentAdapter: BaseAdapter<BookViewModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BookViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_base, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BookViewHolder).bind(items[position])
    }

    private class BookViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(bookVM: BookViewModel) {

        }
    }
}