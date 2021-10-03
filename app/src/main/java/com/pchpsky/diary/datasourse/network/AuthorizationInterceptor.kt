package com.pchpsky.diary.datasourse.network

import android.content.Context
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(val context: Context, private val token: String?) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        Log.d("user_token", "$token")
        request.addHeader(
            "Authorization",
            "Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJkaWFyeSIsImV4cCI6MTYzMzUzMTEyNCwiaWF0IjoxNjMzMjcxOTI0LCJpc3MiOiJkaWFyeSIsImp0aSI6IjcyNWIyYjAwLWI3MmYtNGRiOS05NzVhLWFkN2UzYzJhMGFjNSIsIm5iZiI6MTYzMzI3MTkyMywic3ViIjoiNDciLCJ0eXAiOiJhY2Nlc3MifQ.Da7QTd8fiESgknxXf5nfSqhGMq1hCQGaF8IwPRwtKtVrHMx_4MWUPTIS6YoxFJpQlekGxYXYJO4132HdJCwT0g"
        )

//        if (token != null) {
//            request.addHeader(
//                "Authorization",
//                "Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJkaWFyeSIsImV4cCI6MTYzMzUzMTEyNCwiaWF0IjoxNjMzMjcxOTI0LCJpc3MiOiJkaWFyeSIsImp0aSI6IjcyNWIyYjAwLWI3MmYtNGRiOS05NzVhLWFkN2UzYzJhMGFjNSIsIm5iZiI6MTYzMzI3MTkyMywic3ViIjoiNDciLCJ0eXAiOiJhY2Nlc3MifQ.Da7QTd8fiESgknxXf5nfSqhGMq1hCQGaF8IwPRwtKtVrHMx_4MWUPTIS6YoxFJpQlekGxYXYJO4132HdJCwT0g"
//            )
//        }
        return chain.proceed(request.build())
    }
}