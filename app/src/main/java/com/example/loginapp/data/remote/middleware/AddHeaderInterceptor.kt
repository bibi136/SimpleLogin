package com.example.loginapp.data.remote.middleware

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AddHeaderInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
                .addHeader("IMSI", "357175048449937")
                .addHeader("IMEI", "510110406068589")
        return chain.proceed(builder.build())
    }
}
