package com.mba.data

import com.mba.models.Book
import com.mba.models.BookCategory

data class BookViewModel(
    val book: Book,
    val title: String,
    val subtitle: String,
    val author: String,
    val publisher: String,
    val publishDate: Long,
    val description: String,
    val isbn_13: String,
    val categories: ArrayList<BookCategory>,
    val pageCount: Int,
    val imageURL: String?,
    val avaregeRating: Float?,
    val addedDate: Long?
)