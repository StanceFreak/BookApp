package com.example.f.ModelRomance


import com.google.gson.annotations.SerializedName

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
)