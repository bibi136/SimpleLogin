package com.example.loginapp.ui.home

import androidx.lifecycle.MutableLiveData
import com.example.loginapp.domain.authentication.entity.User
import com.example.loginapp.domain.authentication.usecase.GetUserUseCase
import com.example.loginapp.ui.common.Resource
import com.example.loginapp.ui.common.base.BaseViewModel

class HomeViewModel(
    private val getUserUseCase: GetUserUseCase
) : BaseViewModel() {

    private val userMutable = MutableLiveData<Resource<User>>()
    internal val userLiveData get() = userMutable

    fun getUser() {
        getUserUseCase.execute()
            .subscribe({
                userMutable.value = Resource.success(it)
            }, {
                userMutable.value = Resource.error(it.message)
            }).collect()
    }
}