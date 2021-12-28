package com.example.f.ModelRomance


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReadingModes(
    @SerializedName("text")
    val text: Boolean?,
    @SerializedName("image")
    val image: Boolean?
) : Parcelable