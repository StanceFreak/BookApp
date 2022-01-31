package com.example.f.Model.Adventure


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class PanelizationSummary(
    @SerializedName("containsEpubBubbles")
    val containsEpubBubbles: Boolean?,
    @SerializedName("containsImageBubbles")
    val containsImageBubbles: Boolean?
) : Parcelable