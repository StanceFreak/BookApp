package com.example.f.Model.BookModel


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BooksResponse(
    val position: Int,
    val title: String,
    @SerializedName("items")
    val items: List<Item>
) : Parcelable