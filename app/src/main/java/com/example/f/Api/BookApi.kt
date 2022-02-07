package com.example.f.Api

import com.example.f.Model.Model.BooksResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApi {

    @GET("/books/v1/volumes?q=subject:romance")
    suspend fun getRomanceBooks(
            @Query("startIndex ") startIndex: Int,
            @Query("maxResults") maxResults: Int
    ): BooksResponse

    @GET("/books/v1/volumes?q=subject:adventure")
    suspend fun getAdventureBooks(
            @Query("startIndex ") startIndex: Int,
            @Query("maxResults") maxResults: Int
    ): BooksResponse

    @GET("/books/v1/volumes?q=inauthor:George%20R.R.%20Martin")
    suspend fun getGeorgeMartinBooks(
            @Query("startIndex ") startIndex: Int,
            @Query("maxResults") maxResults: Int
    ): BooksResponse
}