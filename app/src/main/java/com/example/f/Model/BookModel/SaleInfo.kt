package com.example.f.Model.BookModel


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SaleInfo(
    @SerializedName("country")
    val country: String?,
    @SerializedName("saleability")
    val saleability: String?,
    @SerializedName("isEbook")
    val isEbook: Boolean?,
    @SerializedName("listPrice")
    val listPrice: ListPrice,
    @SerializedName("retailPrice")
    val retailPrice: RetailPrice,
    @SerializedName("buyLink")
    val buyLink: String,
//    @SerializedName("offers")
//    val offers: List<Offer>,
    @SerializedName("offers")
    val offers: Offer
) : Parcelable