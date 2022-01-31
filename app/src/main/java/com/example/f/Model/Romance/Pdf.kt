package com.example.f.Model.Romance


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pdf(
    @SerializedName("isAvailable")
    val isAvailable: Boolean?
) : Parcelable