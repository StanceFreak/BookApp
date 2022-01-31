package com.example.f.Model.Adventure


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class AdventureBooksResponse(
    @SerializedName("kind")
    val kind: String,
    @SerializedName("totalItems")
    val totalItems: Int,
    @SerializedName("items")
    val items: List<Item>
) : Parcelable