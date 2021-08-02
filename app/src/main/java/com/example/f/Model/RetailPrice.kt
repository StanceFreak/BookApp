package com.example.f.Model


import com.google.gson.annotations.SerializedName

data class RetailPrice(
    @SerializedName("amount")
    val amount: Double,
    @SerializedName("currencyCode")
    val currencyCode: String
)