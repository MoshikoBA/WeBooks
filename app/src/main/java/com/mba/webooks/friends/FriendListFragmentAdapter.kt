package com.mba.webooks.friends

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mba.basescreens.BaseAdapter
import com.mba.data.BookViewModel
import com.mba.webooks.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_my_book_list_item.view.*
import java.text.SimpleDateFormat
import java.util.*

class FriendListFragmentAdapter : BaseAdapter<BookViewModel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BookViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_my_book_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BookViewHolder).bind(items[position])
    }

    private class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)

        fun bind(bookVM: BookViewModel) {
            itemView.bookTitle.text = bookVM.title
            itemView.bookSubtitle.text = bookVM.author
            itemView.bookPublishDate.text = dateFormatter.format(bookVM.publishDate)

            var categoriesString = ""

            bookVM.categories.forEach {
                categoriesString = "$categoriesString, ${it.toString}"
            }

            categoriesString = categoriesString.removeRange(0, 2)
            itemView.bookCategoties.text = categoriesString

            var addedDateString = "No date"

            bookVM.addedDate?.let {
                addedDateString = dateFormatter.format(it)
            }

            itemView.bookAddedDate.text = addedDateString
            bookVM.imageURL?.let {
                Picasso.get().load(it)
                    .placeholder(R.drawable.ic_img_placeholder).into(itemView.bookImage)
            }

        }
    }
}