package com.mba.models

class Book(

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
    Fiction("Fiction")
}