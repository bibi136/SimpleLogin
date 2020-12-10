package com.example.loginapp.data.authentication.repository

import com.example.loginapp.data.authentication.AuthenticationApi
import com.example.loginapp.data.authentication.UserDao
import com.example.loginapp.data.authentication.request.LoginRequest
import com.example.loginapp.data.remote.error.LoginHttpException
import com.example.loginapp.domain.authentication.AuthenticationRepository
import com.example.loginapp.domain.authentication.entity.User
import io.reactivex.Single

class AuthenticationDataRepository(
    private val api: AuthenticationApi,
    private val dao: UserDao
) : AuthenticationRepository {

    override fun login(username: String, password: String): Single<User> {
        return api.login(LoginRequest(username, password))
            .map {
                val loginResponse = it.body()!!
                if (loginResponse.errorCode != "00") {
                    throw LoginHttpException(loginResponse.errorMessage)
                }
                val token = it.headers().get("X-Acc") ?: ""
                User(loginResponse.user!!.userId, loginResponse.user.userName, token)
            }
            .map {
                // Save user to room database
                dao.insert(it)
                it
            }
    }

    override fun getUserFromDatabase(): Single<User> = dao.getFirst()
}