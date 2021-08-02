package com.example.f.Api


import com.example.f.Model.BookItem
import com.example.f.Model.Books
import retrofit2.Call
import retrofit2.http.GET

interface BookApi {

    @GET("/books/v1/volumes?q=novel")
    fun getBooks(): Call<List<Books>>
}