package com.example.musicapp.data.cloud.daily_mix


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DailyMixCloud(
    @SerializedName("objectId")
    val objectId: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("file")
    val file: MusicFileModel,
    @SerializedName("avatar")
    val avatar: MusicFileModel,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("updatedAt")
    val updatedAt: String
) : Serializable
{
    companion object {
        val unknown = DailyMixCloud(
            "","","",MusicFileModel.unknown, MusicFileModel.unknown,"",""
        )
    }
}

data class MusicFileModel(
    @SerializedName("__type") val type: String,
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
) : Serializable {
    companion object {
        val unknown = MusicFileModel(
            "", "", ""
        )
    }
}