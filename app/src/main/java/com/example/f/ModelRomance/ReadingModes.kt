package com.example.f.ModelRomance


import com.google.gson.annotations.SerializedName

data class ReadingModes(
    @SerializedName("text")
    val text: Boolean,
    @SerializedName("image")
    val image: Boolean
)