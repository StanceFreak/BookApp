package com.example.f.Model


import com.google.gson.annotations.SerializedName

data class Pdf(
    @SerializedName("isAvailable")
    val isAvailable: Boolean,
    @SerializedName("acsTokenLink")
    val acsTokenLink: String
)