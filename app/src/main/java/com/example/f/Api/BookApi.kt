package com.example.f.Api


import com.example.f.ModelRomance.RomanceBooks
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApi {

    @GET("/books/v1/volumes?q=subject:romance")
    fun getRomanceBooks(
            @Query("startIndex ") startIndex: Int,
            @Query("maxResults") maxResults: Int
    ): Call<RomanceBooks>

}