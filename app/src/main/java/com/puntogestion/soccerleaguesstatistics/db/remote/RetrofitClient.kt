package com.puntogestion.soccerleaguesstatistics.db.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.puntogestion.soccerleaguesstatistics.db.remote.Interceptor as Interceptor

class RetrofitClient {
    companion object{
        private const val BASE_URL = "http://api.football-data.org/"
        fun retrofitInstance(): SoccerApi{
            var interceptor = Interceptor()
            var cliente = OkHttpClient.Builder().addInterceptor(interceptor).build()

            var retrofitClient = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(cliente)
                .build()
            return retrofitClient.create(SoccerApi::class.java)
        }
    }
}