package com.example.f.ModelRomance


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Epub(
    @SerializedName("isAvailable")
    val isAvailable: Boolean?,
    @SerializedName("acsTokenLink")
    val acsTokenLink: String?
) : Parcelable