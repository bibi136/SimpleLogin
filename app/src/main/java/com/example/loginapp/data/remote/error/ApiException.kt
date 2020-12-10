package com.example.loginapp.data.remote.error

import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException

class ApiException(throwable: Throwable?) : Throwable() {
    override val message get() = _message

    private var _message: String = ""
    private var _execTime: String = ""
    private var _errorCode: ErrorCode = ErrorCode.UNEXPECTED_EXCEPTION

    init {
        when (throwable) {
            is HttpException -> httpError(throwable)
            is UnknownHostException,
            is IOException -> networkError()
            null -> Unit
            else -> unexpectedError(throwable)
        }
    }

    private fun httpError(error: HttpException) {
        val errorBody = JSONObject(error.response().errorBody()?.string() ?: "{}")
        _message = errorBody.getString(ERROR_KEY)
        _execTime = errorBody.getString(EXEC_TIME_KEY)
        _errorCode = ErrorCode.HTTP_EXCEPTION
    }

    private fun networkError() {
        _errorCode = ErrorCode.NETWORK_EXCEPTION
    }

    private fun unexpectedError(error: Throwable) {
        _errorCode = ErrorCode.UNEXPECTED_EXCEPTION
        error.printStackTrace()
    }

    companion object {
        private const val ERROR_KEY = "error"
        private const val EXEC_TIME_KEY = "exectime"
    }
}
