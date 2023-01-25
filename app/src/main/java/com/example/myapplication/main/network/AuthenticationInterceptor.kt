package com.example.myapplication.main.network

import android.util.Log
import com.example.myapplication.main.TestApplication
import okhttp3.Interceptor

class AuthenticationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val accessToken = TestApplication.encryptedPrefs.getAccessToken() ?: ""
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $accessToken").build()
        Log.d(
            "RETROFIT_TAG",
            "AuthenticationInterceptor - intercept() called / request header: ${request.headers}"
        )
        return chain.proceed(request)
    }
}