package com.mba.models

class Book(

    val title: String,
    val subtitle: String?,
    val author: String,
    val publisher: String?,
    val publishDate: Long?,
    val description: String?,
    val isbn_13: String,
    val categories: ArrayList<BookCategory>?,
    val pageCount: Int?,
    val imageURL: String?,
    val avaregeRating: Float?
) {

}



enum class BookCategory {

}