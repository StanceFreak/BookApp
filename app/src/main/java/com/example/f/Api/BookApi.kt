package com.example.f.Api


import com.example.f.Model.Romance.BooksResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApi {

//    @GET("/books/v1/volumes?q=subject:romance")
//    suspend fun getRomanceBooks(
////            @Path("id") id: String,
//            @Query("startIndex ") startIndex: Int,
//            @Query("maxResults") maxResults: Int
//    ): RomanceBooks
//
//    @GET("/books/v1/volumes?q=subject:adventure")
//    suspend fun getAdventureBooks(
//            @Query("startIndex ") startIndex: Int,
//            @Query("maxResults") maxResults: Int
//    ): AdventureBooks

    @GET("/books/v1/volumes?q=subject:romance")
    suspend fun getRomanceBooks(
//            @Path("id") id: String,
            @Query("startIndex ") startIndex: Int,
            @Query("maxResults") maxResults: Int
    ): BooksResponse

    @GET("/books/v1/volumes?q=subject:adventure")
    suspend fun getAdventureBooks(
            @Query("startIndex ") startIndex: Int,
            @Query("maxResults") maxResults: Int
    ): BooksResponse

}