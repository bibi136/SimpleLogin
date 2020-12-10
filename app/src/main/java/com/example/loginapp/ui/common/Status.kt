package com.example.loginapp.ui.common

enum class Status {
    SUCCESS,
    ERROR,
    LOADING;

    fun isLoading() = this == LOADING
}