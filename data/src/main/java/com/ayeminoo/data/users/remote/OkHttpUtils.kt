package com.ayeminoo.data.users.remote

import okhttp3.OkHttpClient
import okhttp3.Request

fun OkHttpClient.Builder.setToken(token: String) = addInterceptor { chain ->
    val request: Request = chain.request()
        .newBuilder()
        .addHeader("Authorization", "token $token")
        .build()
    chain.proceed(request)
}.build()