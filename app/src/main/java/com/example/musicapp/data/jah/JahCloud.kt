package com.example.musicapp.data.jah


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class JahCloud(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("file")
    val file: JahModel,
    @SerializedName("objectId")
    val objectId: JahModel,
    @SerializedName("title")
    val title: String,
    @SerializedName("updatedAt")
    val updatedAt: String
) : Serializable {
    companion object {
        val unknown = JahCloud(
            "", "", "",
            JahModel.unknown, JahModel.unknown, "", ""
        )
    }
}

data class JahModel(
    @SerializedName("__type") val type: String,
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
) : Serializable {
    companion object {
        val unknown = JahModel(
            "",
            "",
            ""
        )
    }
}
