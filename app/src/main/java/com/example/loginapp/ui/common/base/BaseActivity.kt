package com.example.loginapp.ui.common.base

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseActivity<B : ViewBinding>(
    private val bindingFactory: (LayoutInflater) -> B
) : AppCompatActivity() {

    private val disposable = CompositeDisposable()

    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingFactory(layoutInflater)
        setContentView(binding.root)
        initViews()
        initData()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    protected fun Disposable.collect() = disposable.add(this)

    @CallSuper
    protected open fun initViews() = Unit

    @CallSuper
    protected open fun initData() = Unit

    fun hideKeyboard() {
        val decorView = window.peekDecorView()
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(decorView.windowToken, 0)
    }
}