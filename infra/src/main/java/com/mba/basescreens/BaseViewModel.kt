package com.mba.basescreens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class UiState {
    Loading, Success, Error
}

enum class SingleOperationState {
    Loading, Success, Error
}

abstract class BaseViewModel : ViewModel() {
    val uiStatLiveData = MutableLiveData<UiState>()
    val singleOperationStateLiveData = MutableLiveData<SingleOperationState>()

    abstract fun loadData()
}