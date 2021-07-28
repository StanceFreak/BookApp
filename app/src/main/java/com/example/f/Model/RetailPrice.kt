package com.example.f.Model


import com.google.gson.annotations.SerializedName

data class RetailPrice(
    @SerializedName("amount")
    val amount: Int,
    @SerializedName("currencyCode")
    val currencyCode: String
)