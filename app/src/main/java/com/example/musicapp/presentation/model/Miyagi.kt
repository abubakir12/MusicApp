package com.example.musicapp.presentation.model

import com.example.musicapp.data.miyagi.MiyagiModel
import com.example.musicapp.domain.models.MiyagiDomain

data class Miyagi(
    val avatar: MiyagiModel,
    val createdAt: String,
    val description: String,
    val file: MiyagiModel,
    val objectId: MiyagiModel,
    val title: String,
    val updatedAt: String
) {
    companion object {
        val unknown = Miyagi(
            avatar = MiyagiModel.unknown,
            createdAt = String(),
            description = String(),
            file = MiyagiModel.unknown,
            objectId = MiyagiModel.unknown,
            title = String(),
            updatedAt = String()
        )
    }
}

fun MiyagiDomain.toMiyagi() = this.run {
    if (this == MiyagiDomain.unknown) return@run Miyagi.unknown
    Miyagi(
        avatar = avatar,
        createdAt = createdAt,
        description = description,
        file = file,
        objectId = MiyagiModel.unknown,
        title = title,
        updatedAt = updatedAt
    )
}