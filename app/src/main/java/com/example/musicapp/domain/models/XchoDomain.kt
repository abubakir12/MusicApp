package com.example.musicapp.domain.models

import com.example.musicapp.data.xcho.XchoModel

data class XchoDomain(
    val avatar: XchoModel,
    val createdAt: String,
    val description: String,
    val file: XchoModel,
    val objectId: String,
    val title: String,
    val updatedAt: String
) {
    companion object {
        val unknown = XchoDomain(
            avatar = XchoModel.unknown,
            createdAt = "",
            description = "",
            file = XchoModel.unknown,
            objectId = String(),
            title = String(),
            updatedAt = String()
        )
    }
}