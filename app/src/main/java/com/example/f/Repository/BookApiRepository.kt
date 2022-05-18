package com.example.f.Repository

import com.example.f.Api.BookApiHelper

class BookApiRepository(private val bookApiHelper: BookApiHelper) {

    suspend fun getBooksByGenre(
            q: String,
            map: Map<String, Any>
    ) = bookApiHelper.getBooksByGenre(q, map)

//     suspend fun getRomanceBooks(
//        startIndex : Int,
//        maxResults : Int
//    ) = bookApiHelper.getRomanceBooks(startIndex, maxResults)
//
//    suspend fun getAdventureBooks(
//            startIndex : Int,
//            maxResults : Int
//    ) = bookApiHelper.getAdventureBooks(startIndex, maxResults)

    suspend fun getGeorgeMartinBooks(
            startIndex : Int,
            maxResults : Int
    ) = bookApiHelper.getGeorgeMartinBooks(startIndex, maxResults)

    suspend fun searchBooks(
            query: String
    ) = bookApiHelper.searchBooks(query)

}