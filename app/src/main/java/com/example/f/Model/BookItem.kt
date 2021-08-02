package com.example.f.Model

import com.google.gson.annotations.SerializedName

data class BookItem(
        @SerializedName("title")
        val title: String,
        @SerializedName("authors")
        val authors: List<String>,
        @SerializedName("pageCount")
        val pageCount: Int,
        @SerializedName("imageLinks")
        val imageLinks: ImageLinks,
        @SerializedName("averageRating")
        val averageRating: Double,
        @SerializedName("ratingsCount")
        val ratingsCount: Int,
        @SerializedName("retailPrice")
        val retailPrice: RetailPrice,
)
