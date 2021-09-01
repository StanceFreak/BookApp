package com.example.f.ModelRomance


import com.google.gson.annotations.SerializedName

data class RomanceBooks(
    @SerializedName("kind")
    val kind: String,
    @SerializedName("totalItems")
    val totalItems: Int,
    @SerializedName("items")
    val items: List<Item>
)