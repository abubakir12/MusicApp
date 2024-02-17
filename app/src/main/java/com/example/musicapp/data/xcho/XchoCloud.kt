package com.example.musicapp.data.xcho


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class XchoCloud(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("file")
    val file: XchoModel,
    @SerializedName("objectId")
    val objectId: XchoModel,
    @SerializedName("title")
    val title: String,
    @SerializedName("updatedAt")
    val updatedAt: String
) : Serializable {
    companion object {
        val unknown = XchoCloud(
            "", "", "",
            XchoModel.unknown, XchoModel.unknown, "", ""
        )
    }
}

data class XchoModel(
    @SerializedName("__type") val type: String,
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
) : Serializable {
    companion object {
        val unknown = XchoModel(
            "",
            "",
            ""
        )
    }
}