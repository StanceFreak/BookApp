package com.example.f.Model


import com.google.gson.annotations.SerializedName

data class Books(
    @SerializedName("kind")
    val kind: String,
    @SerializedName("totalItems")
    val totalItems: Int,
    @SerializedName("items")
    val items: List<Item>
)