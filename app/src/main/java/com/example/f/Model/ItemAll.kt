package com.example.f.Model

import android.os.Parcelable
import com.example.f.Model.BookModel.Item
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemAll(
    @SerializedName("items")
    val items: List<Item>
): Parcelable