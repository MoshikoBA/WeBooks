package com.mba.webooks

import com.mba.models.Book
import com.mba.models.BookCategory
import java.text.SimpleDateFormat
import java.util.*

class MockData {

    companion object {
        private val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)

        val books = arrayListOf(
            Book(
                title = "Home",
                author = "Harlan Coben",
                publisher = "Penguin",
                publishDate = dateFormatter.parse("2016-09-20")?.time,
                isbn_13 = "9780698411449",
                categories = arrayListOf(BookCategory.Fiction),
                imageURL = "http://books.google.com/books/content?id=1IG0CwAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api",
                avaregeRating = 7.6f
                ),
            Book(
                title = "Found",
                author = "Harlan Coben",
                publisher = "Hachette UK",
                publishDate = dateFormatter.parse("2014-09-11")?.time,
                isbn_13 = "9781409124535",
                categories = arrayListOf(BookCategory.Fiction),
                imageURL = "http://books.google.com/books/content?id=oRS7AwAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api",
                avaregeRating = 5.6f
            ),
            Book(
                title = "One False Move",
                author = "Harlan Coben",
                publisher = "Hachette UK",
                publishDate = dateFormatter.parse("2009-12-23")?.time,
                isbn_13 = "9781409121756",
                categories = arrayListOf(BookCategory.Fiction),
                imageURL = "http://books.google.com/books/content?id=xZx74JxGbg0C&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api",
                avaregeRating = 7.6f
            )
        )
    }
}