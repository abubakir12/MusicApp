package com.example.musicapp.presentation.model

import com.example.musicapp.data.xcho.XchoModel
import com.example.musicapp.domain.models.XchoDomain

data class Xcho(
    val avatar: XchoModel,
    val createdAt: String,
    val description: String,
    val file: XchoModel,
    val objectId: XchoModel,
    val title: String,
    val updatedAt: String
) {
    companion object {
        val unknown = Xcho(
            avatar = XchoModel.unknown,
            createdAt = String(),
            description = String(),
            file = XchoModel.unknown,
            objectId = XchoModel.unknown,
            title = String(),
            updatedAt = String()
        )
    }
}

fun XchoDomain.toXcho() = this.run {
    if (this == XchoDomain.unknown) return@run Xcho.unknown
    Xcho(
        avatar = XchoModel.unknown,
        createdAt = createdAt,
        description = description,
        file = XchoModel.unknown,
        objectId = XchoModel.unknown,
        title = title,
        updatedAt = updatedAt
    )
}