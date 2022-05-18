package com.example.f.Api

import com.example.f.Model.BookModel.BooksResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface BookApi {

    @GET("/books/v1/volumes?")
    @JvmSuppressWildcards
    suspend fun getBooksByGenre(
            @Query ("q") q: String,
            @QueryMap map: Map<String, Any>
    ): BooksResponse

//    @GET("/books/v1/volumes?q=subject:romance")
//    suspend fun getRomanceBooks(
//            @Query("startIndex ") startIndex: Int,
//            @Query("maxResults") maxResults: Int
//    ): BooksResponse
//
//    @GET("/books/v1/volumes?q=subject:adventure")
//    suspend fun getAdventureBooks(
//            @Query("startIndex ") startIndex: Int,
//            @Query("maxResults") maxResults: Int
//    ): BooksResponse

    @GET("/books/v1/volumes?q=inauthor:George%20R.R.%20Martin")
    suspend fun getGeorgeMartinBooks(
            @Query("startIndex ") startIndex: Int,
            @Query("maxResults") maxResults: Int
    ): BooksResponse

    @GET("/books/v1/volumes?q={query}")
    suspend fun searchBooks(
            @Query("query") query: String
    ): BooksResponse
}