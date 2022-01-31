package com.example.f.Model.Romance


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BooksResponse(
    @SerializedName("kind")
    val kind: String,
    @SerializedName("totalItems")
    val totalItems: Int,
    @SerializedName("items")
    val items: List<Item>
) : Parcelable