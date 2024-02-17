package com.example.musicapp.presentation.model

import com.example.musicapp.data.macan.MacanModel
import com.example.musicapp.data.xcho.XchoModel
import com.example.musicapp.domain.models.MacanDomain

data class Macan(
    val avatar: MacanModel,
    val createdAt: String,
    val description: String,
    val file: MacanModel,
    val objectId: String,
    val title: String,
    val updatedAt: String
) {
    companion object {
        val unknown = Macan(
            avatar = MacanModel.unknown,
            createdAt = String(),
            description = String(),
            file = MacanModel.unknown,
            objectId = String(),
            title = String(),
            updatedAt = String(),
        )
    }
}

fun MacanDomain.toMacan() = this.run {
    if (this == MacanDomain.unknown) return@run Macan.unknown
    Macan(
        avatar = avatar,
        createdAt = createdAt,
        description = description,
        file = file,
        objectId = objectId,
        title = title,
        updatedAt = updatedAt
    )
}