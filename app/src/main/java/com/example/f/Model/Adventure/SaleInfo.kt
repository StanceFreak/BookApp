package com.example.f.Model.Adventure


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class SaleInfo(
    @SerializedName("country")
    val country: String?,
    @SerializedName("saleability")
    val saleability: String?,
    @SerializedName("isEbook")
    val isEbook: Boolean?
) : Parcelable