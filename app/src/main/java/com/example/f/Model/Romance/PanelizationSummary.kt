package com.example.f.Model.Romance


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PanelizationSummary(
    @SerializedName("containsEpubBubbles")
    val containsEpubBubbles: Boolean?,
    @SerializedName("containsImageBubbles")
    val containsImageBubbles: Boolean?
) : Parcelable