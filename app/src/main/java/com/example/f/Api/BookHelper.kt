package com.example.f.Api

class BookHelper (private val bookApi: BookApi){
    suspend fun getRomanceBooks(
        startIndex: Int,
        maxResults : Int
    ) = bookApi.getRomanceBooks(startIndex, maxResults)
}