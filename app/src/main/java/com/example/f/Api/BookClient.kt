package com.example.f.Api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object BookClient {

    private const val BASE_URL = "https://www.googleapis.com"

    val instance: BookApi by lazy {

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val hClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(hClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        retrofit.create(BookApi::class.java)
    }

}
