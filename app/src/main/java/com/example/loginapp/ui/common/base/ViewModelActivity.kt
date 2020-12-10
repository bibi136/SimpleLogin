package com.example.loginapp.ui.common.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.loginapp.ui.ViewModelFactory

abstract class ViewModelActivity<T : BaseViewModel, B : ViewBinding>(
    bindingFactory: (LayoutInflater) -> B
) : BaseActivity<B>(bindingFactory) {

    protected val viewModel: T by lazy {
        ViewModelProvider(
            this,
            ViewModelFactory.getInstance(application)
        ).get(getViewModelClass())
    }
    protected abstract fun getViewModelClass(): Class<T>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLiveData()
    }

    protected open fun observeLiveData() = Unit
}