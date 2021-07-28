package com.example.f.Model


import com.google.gson.annotations.SerializedName

data class RetailPriceX(
    @SerializedName("amountInMicros")
    val amountInMicros: Long,
    @SerializedName("currencyCode")
    val currencyCode: String
)