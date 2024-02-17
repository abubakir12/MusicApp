package com.example.musicapp.data.miyagi


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MiyagiCloud(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("file")
    val file: MiyagiModel,
    @SerializedName("objectId")
    val objectId: MiyagiModel,
    @SerializedName("title")
    val title: String,
    @SerializedName("updatedAt")
    val updatedAt: String
) : Serializable{
    companion object {
        val unknown = MiyagiCloud(
            "", "", "",
            MiyagiModel.unknown, MiyagiModel.unknown, "", ""
        )
    }
}

data class MiyagiModel(
    @SerializedName("__type") val type: String,
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
) : Serializable {
    companion object {
        val unknown = MiyagiModel(
            "",
            "",
            ""
        )
    }
}