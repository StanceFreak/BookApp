package com.example.f.Model.Adventure


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class IndustryIdentifier(
    @SerializedName("type")
    val type: String?,
    @SerializedName("identifier")
    val identifier: String?
) : Parcelable