package com.example.loginapp.domain.authentication.usecase

import com.example.loginapp.domain.authentication.AuthenticationRepository
import com.example.loginapp.domain.authentication.entity.User
import com.example.loginapp.domain.common.BaseUseCase
import com.example.loginapp.domain.common.UseCaseConstant
import io.reactivex.Single
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : BaseUseCase<User>() {

    override fun buildSingle(data: Map<String, Any?>): Single<User> {
        val userName = data[UseCaseConstant.USER_NAME] as String
        val password = data[UseCaseConstant.PASSWORD] as String
        return authenticationRepository.login(userName, password)
    }
}