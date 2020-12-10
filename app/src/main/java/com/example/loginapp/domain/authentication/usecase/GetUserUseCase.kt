package com.example.loginapp.domain.authentication.usecase

import com.example.loginapp.domain.authentication.AuthenticationRepository
import com.example.loginapp.domain.authentication.entity.User
import com.example.loginapp.domain.common.BaseUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : BaseUseCase<User>() {

    override fun buildSingle(data: Map<String, Any?>): Single<User> {
        return authenticationRepository.getUserFromDatabase()
    }
}