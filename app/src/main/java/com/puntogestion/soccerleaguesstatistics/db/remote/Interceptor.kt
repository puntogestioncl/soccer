package com.puntogestion.soccerleaguesstatistics.db.remote

import okhttp3.Interceptor
import okhttp3.Response

class Interceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var originalRequest = chain.request()
        var newRequest = originalRequest.newBuilder()
            .header("X-Auth-Token", "775591e8d793427493829ba829bc496c")
            .build()
        return chain.proceed(newRequest)
    }
}