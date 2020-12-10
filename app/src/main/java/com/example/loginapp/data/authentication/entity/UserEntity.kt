package com.example.loginapp.data.authentication.entity

import com.google.gson.annotations.SerializedName

data class UserEntity(
    @SerializedName("userId") val userId: Long,
    @SerializedName("userName") val userName: String,
)