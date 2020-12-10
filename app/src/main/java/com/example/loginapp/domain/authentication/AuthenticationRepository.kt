package com.example.loginapp.domain.authentication

import com.example.loginapp.domain.authentication.entity.User
import io.reactivex.Single

interface AuthenticationRepository {

    fun login(username: String, password: String): Single<User>

    fun getUserFromDatabase(): Single<User>
}