package com.example.f.ModelFantasy


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class IndustryIdentifier(
    @SerializedName("type")
    val type: String,
    @SerializedName("identifier")
    val identifier: String
) : Parcelable