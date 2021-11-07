package com.example.f.ModelFantasy


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    @SerializedName("kind")
    val kind: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("etag")
    val etag: String,
    @SerializedName("selfLink")
    val selfLink: String,
    @SerializedName("volumeInfo")
    val volumeInfo: VolumeInfo,
    @SerializedName("saleInfo")
    val saleInfo: SaleInfo,
    @SerializedName("accessInfo")
    val accessInfo: AccessInfo
) : Parcelable