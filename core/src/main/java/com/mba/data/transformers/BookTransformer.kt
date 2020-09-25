package com.mba.data.transformers

import com.mba.data.BookViewModel
import com.mba.models.Book

class BookTransformer  : Transformer<Book, BookViewModel> {

    override fun transform(book: Book): BookViewModel =
        BookViewModel(book,
            book.title,
            book.subtitle ?: "",
            book.author,
            book.publisher ?: "",
            book.publishDate ?: 0L,
            book.description ?: "",
            book.isbn_13,
            book.categories ?: arrayListOf(),
            book.pageCount ?: 0,
            book.imageURL,
            book.avaregeRating ?: 0f
        )

}