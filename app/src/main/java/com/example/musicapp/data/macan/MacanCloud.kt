package com.example.musicapp.data.macan


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MacanCloud(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("file")
    val file: MacanModel,
    @SerializedName("objectId")
    val objectId: MacanModel,
    @SerializedName("title")
    val title: String,
    @SerializedName("updatedAt")
    val updatedAt: String
) : Serializable {
    companion object {
        val unknown = MacanCloud(
            "", "", "",
            MacanModel.unknown, MacanModel.unknown, "", ""
        )
    }
}

data class MacanModel(
    @SerializedName("__type") val type: String,
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
) : Serializable {
    companion object {
        val unknown = MacanModel(
            "",
            "",
            ""
        )
    }
}