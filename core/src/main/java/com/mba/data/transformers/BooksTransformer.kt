package com.mba.data.transformers

import com.mba.data.BookViewModel
import com.mba.models.Book

class BooksTransformer : Transformer<List<Book>, List<BookViewModel>> {

    override fun transform(data: List<Book>): List<BookViewModel> =
        data.map { BookTransformer().transform(it) }
}