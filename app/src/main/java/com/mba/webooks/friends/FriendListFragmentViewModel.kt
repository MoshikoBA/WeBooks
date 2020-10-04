package com.mba.webooks.friends

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import com.mba.basescreens.BaseViewModel
import com.mba.basescreens.UiState
import com.mba.data.BookViewModel
import com.mba.data.transformers.BooksTransformer
import com.mba.webooks.MockData

class FriendListFragmentViewModel : BaseViewModel() {
    val friendsLiveData = MutableLiveData<List<BookViewModel>>()


    override fun loadData() {
        uiStatLiveData.postValue(UiState.Loading)
        Handler().postDelayed({
            friendsLiveData.postValue(BooksTransformer().transform(MockData.books))
            uiStatLiveData.postValue(UiState.Success)
        }, 1000L)

    }
}