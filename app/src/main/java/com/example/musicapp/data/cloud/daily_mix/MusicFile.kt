package com.example.musicapp.data.cloud.daily_mix


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

data class MusicFile(
    @SerializedName("name")
    val name: String,
    @SerializedName("__type")
    val type: String,
    @SerializedName("url")
    val url: String
) : Serializable