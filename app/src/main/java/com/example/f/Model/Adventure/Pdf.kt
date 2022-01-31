package com.example.f.Model.Adventure


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Pdf(
    @SerializedName("isAvailable")
    val isAvailable: Boolean?,
    @SerializedName("acsTokenLink")
    val acsTokenLink: String?
) : Parcelable