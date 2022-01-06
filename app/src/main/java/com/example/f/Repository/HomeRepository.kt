package com.example.f.Repository

import com.example.f.Api.BookHelper

class HomeRepository(private val bookHelper: BookHelper) {

     suspend fun getRomanceBooks(
        startIndex : Int,
        maxResults : Int
    ) = bookHelper.getRomanceBooks(startIndex, maxResults)

}