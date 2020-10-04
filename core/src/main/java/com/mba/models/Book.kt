package com.mba.models

import com.mba.Core

class Book(
    title: String,
    subtitle: String? = null,
    author: String,
    publisher: String?,
    publishDate: Long?,
    description: String? = null,
    isbn_13: String,
    categories: ArrayList<BookCategory>?,
    pageCount: Int? = null,
    imageURL: String?,
    avaregeRating: Float?,
    val addedDate: Long?
) : BookData(
    title,
    subtitle,
    author,
    publisher,
    publishDate,
    description,
    isbn_13,
    categories,
    pageCount,
    imageURL,
    avaregeRating
) {

}

open class BookData(

    val title: String,
    val subtitle: String? = null,
    val author: String,
    val publisher: String?,
    val publishDate: Long?,
    val description: String? = null,
    val isbn_13: String,
    val categories: ArrayList<BookCategory>?,
    val pageCount: Int? = null,
    val imageURL: String?,
    val avaregeRating: Float?
) {

}


enum class BookCategory(val toString: String) {
    FICTION(Core.context.getString(R.string.fiction)),
    SHORT_STORIES(Core.context.getString(R.string.short_stories))
}