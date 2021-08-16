package com.example.f.Repository

import com.example.f.Api.BookClient
import com.example.f.Model.BookItem
import com.example.f.Model.Books
import retrofit2.Call

class BookRepository {

    fun getBooks(): Call<Books> {
        return BookClient.instance.getBooks()
    }

}