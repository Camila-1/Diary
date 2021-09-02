package com.pchpsky.diary.datasourse.network

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(val context: Context): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("authorization", "token")
            .build()
        return chain.proceed(request)
    }
}