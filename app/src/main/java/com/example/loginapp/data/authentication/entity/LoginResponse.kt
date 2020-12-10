package com.example.loginapp.data.authentication.entity

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("errorCode") val errorCode: String,
    @SerializedName("errorMessage") val errorMessage: String,
    @SerializedName("user") val user: UserEntity?
)
