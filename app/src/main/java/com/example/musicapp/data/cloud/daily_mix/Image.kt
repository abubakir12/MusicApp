package com.example.musicapp.data.cloud.daily_mix

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.Serializable

data class Image(
    @SerializedName("name")
    val name: String = "icon.png",
    @SerializedName("__type")
    val type: String = "File",
    @SerializedName("url")
    val url: String
) : Serializable