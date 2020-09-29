package com.mba.basescreens

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import java.lang.reflect.ParameterizedType

abstract class AbstractViewModelFragment<V : ViewModel> : BaseFragment() {

    val viewModel: V by lazy { createViewModel() }

    open fun createViewModel(): V = ViewModelProviders.of(this).get(getViewModelClass())

    private fun getViewModelClass(): Class<V> {
        return (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<V>
    }

    abstract fun initializeViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViewModel()
    }
}
