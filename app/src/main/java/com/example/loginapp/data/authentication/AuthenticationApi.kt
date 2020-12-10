package com.example.loginapp.data.authentication

import com.example.loginapp.data.authentication.entity.LoginResponse
import com.example.loginapp.data.authentication.request.LoginRequest
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationApi {

    @POST("/api/login")
    fun login(
        @Body loginRequest: LoginRequest
    ): Single<Response<LoginResponse>>
}