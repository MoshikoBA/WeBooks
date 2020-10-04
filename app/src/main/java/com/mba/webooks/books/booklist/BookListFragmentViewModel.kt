package com.mba.webooks.books.booklist

import androidx.lifecycle.MutableLiveData
import com.mba.basescreens.BaseViewModel
import com.mba.data.BookViewModel
import com.mba.data.transformers.BooksTransformer
import com.mba.webooks.MockData

class BookListFragmentViewModel : BaseViewModel() {
    val booksLiveData = MutableLiveData<List<BookViewModel>>()


    override fun loadData() {
        booksLiveData.postValue(BooksTransformer().transform(MockData.books))
    }
}