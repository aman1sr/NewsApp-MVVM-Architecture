package com.aman.mynewsmvvm_cleanarch.data.api

import com.aman.mynewsmvvm_cleanarch.di.NetworkAPIKey
import okhttp3.Interceptor
import okhttp3.Response

class AuthTokenInterceptor(@NetworkAPIKey private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalReq = chain.request()
        val reqBuilder = originalReq.newBuilder().header("X-Api-Key", apiKey)
        val request = reqBuilder.build()

        return chain.proceed(request)
    }
}