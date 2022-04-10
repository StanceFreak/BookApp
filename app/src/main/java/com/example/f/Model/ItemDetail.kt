package com.example.f.Model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemDetail(
    @SerializedName("id")
    val id: String?,
    @SerializedName("thumbnail")
    val thumbnail: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("authors")
    val authors: String?,
    @SerializedName("averageRating")
    val averageRating: Float?,
    @SerializedName("ratingsCount")
    val ratingsCount: Int?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("amountInMicros")
    val amountInMicros: Long?,
    @SerializedName("pageCount")
    val pageCount: Int?,
    @SerializedName("previewLink")
    val previewLink: String?
) : Parcelable
