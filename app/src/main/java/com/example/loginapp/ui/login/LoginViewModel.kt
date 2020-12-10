package com.example.loginapp.ui.login

import androidx.lifecycle.MutableLiveData
import com.example.loginapp.data.remote.error.LoginHttpException
import com.example.loginapp.domain.authentication.entity.User
import com.example.loginapp.domain.authentication.usecase.LoginUseCase
import com.example.loginapp.domain.common.UseCaseConstant
import com.example.loginapp.ui.common.Resource
import com.example.loginapp.ui.common.base.BaseViewModel

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    private val loginMutable = MutableLiveData<Resource<User>>()
    internal val loginLiveData get() = loginMutable

    fun login(userName: String, password: String) {
        loginMutable.value = Resource.loading()
        loginUseCase.execute(
            mapOf(
                UseCaseConstant.USER_NAME to userName,
                UseCaseConstant.PASSWORD to password
            )
        ).subscribe({
            loginMutable.value = Resource.success(it)
        }, {
            handleErrorException(it)
        }).collect()
    }

    private fun handleErrorException(throwable: Throwable) {
        loginMutable.value = when (throwable) {
            is LoginHttpException -> Resource.error(throwable.message)
            else -> Resource.error("No network")
        }
    }
}