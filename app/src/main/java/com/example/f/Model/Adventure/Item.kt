package com.example.f.Model.Adventure


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Item(
    @SerializedName("kind")
    val kind: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("etag")
    val etag: String?,
    @SerializedName("selfLink")
    val selfLink: String?,
    @SerializedName("volumeInfo")
    val volumeInfo: VolumeInfo?,
    @SerializedName("saleInfo")
    val saleInfo: SaleInfo?,
    @SerializedName("accessInfo")
    val accessInfo: AccessInfo?
) : Parcelable