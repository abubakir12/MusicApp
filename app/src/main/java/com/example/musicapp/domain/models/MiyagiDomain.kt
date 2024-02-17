package com.example.musicapp.domain.models

import com.example.musicapp.data.miyagi.MiyagiModel

data class MiyagiDomain(
    val avatar: MiyagiModel,
    val createdAt: String,
    val description: String,
    val file: MiyagiModel,
    val objectId: String,
    val title: String,
    val updatedAt: String
) {
    companion object {
        val unknown = MiyagiDomain(
            avatar = MiyagiModel.unknown,
            createdAt = String(),
            description = String(),
            file = MiyagiModel.unknown,
            objectId = String(),
            title = String(),
            updatedAt = String()
        )
    }
}