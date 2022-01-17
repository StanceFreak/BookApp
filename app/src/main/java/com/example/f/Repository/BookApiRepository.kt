package com.example.f.Repository

import com.example.f.Api.BookApiHelper

class BookApiRepository(private val bookApiHelper: BookApiHelper) {

     suspend fun getRomanceBooks(
        startIndex : Int,
        maxResults : Int
    ) = bookApiHelper.getRomanceBooks(startIndex, maxResults)


}