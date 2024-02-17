package com.example.musicapp.data.miyagi


import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class File(
    @SerializedName("name")
    val name: String,
    @SerializedName("__type")
    val type: String,
    @SerializedName("url")
    val url: String
) : Serializable