package com.example.loginapp.ui

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.loginapp.domain.authentication.usecase.GetUserUseCase
import com.example.loginapp.domain.authentication.usecase.LoginUseCase
import com.example.loginapp.ui.app.App
import com.example.loginapp.ui.home.HomeViewModel
import com.example.loginapp.ui.login.LoginViewModel
import javax.inject.Inject

class ViewModelFactory(application: Application) : ViewModelProvider.Factory {

    @Inject
    lateinit var loginUseCase: LoginUseCase

    @Inject
    lateinit var getUserUseCase: GetUserUseCase

    init {
        (application as App).viewModelFactoryComponent.inject(this)
    }

    @Suppress("IMPLICIT_CAST_TO_ANY", "UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(LoginViewModel::class.java) ->
                    LoginViewModel(loginUseCase)
                isAssignableFrom(HomeViewModel::class.java) ->
                    HomeViewModel(getUserUseCase)
                else ->
                    throw IllegalAccessException("Unknow ViewModel class ${modelClass.name}")
            }
        } as T

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(application: Application) =
            INSTANCE ?: synchronized(ViewModelFactory::class.java) {
                INSTANCE ?: ViewModelFactory(application)
                    .also { INSTANCE = it }
            }
    }
}