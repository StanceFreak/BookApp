package com.example.f.Model.Adventure


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class ReadingModes(
    @SerializedName("text")
    val text: Boolean?,
    @SerializedName("image")
    val image: Boolean?
) : Parcelable