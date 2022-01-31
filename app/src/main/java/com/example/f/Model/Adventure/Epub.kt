package com.example.f.Model.Adventure


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Epub(
    @SerializedName("isAvailable")
    val isAvailable: Boolean?
) : Parcelable