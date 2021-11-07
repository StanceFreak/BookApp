package com.example.f.Api


import com.example.f.ModelFantasy.FantasyBooks
import com.example.f.ModelRomance.RomanceBooks
import retrofit2.Call
import retrofit2.http.GET

interface BookApi {

    @GET("/books/v1/volumes?q=subject:romance")
    fun getRomanceBooks(): Call<RomanceBooks>

    @GET("/books/v1/volumes?q=subject:fantasy")
    fun getFantasyBooks(): Call<FantasyBooks>
}