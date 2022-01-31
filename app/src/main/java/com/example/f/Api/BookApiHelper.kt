package com.example.f.Api

class BookApiHelper (private val bookApi: BookApi){
    suspend fun getRomanceBooks(
        startIndex: Int,
        maxResults : Int
    ) = bookApi.getRomanceBooks(startIndex, maxResults)

    suspend fun getAdventureBooks(
            startIndex: Int,
            maxResults : Int
    ) = bookApi.getAdventureBooks(startIndex, maxResults)
}