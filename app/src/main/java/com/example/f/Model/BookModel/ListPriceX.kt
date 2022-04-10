package com.example.f.Model.BookModel


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class ListPriceX(
    @SerializedName("amountInMicros")
    val amountInMicros: Long,
    @SerializedName("currencyCode")
    val currencyCode: String
) : Parcelable