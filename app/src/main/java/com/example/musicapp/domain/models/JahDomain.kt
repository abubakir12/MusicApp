package com.example.musicapp.domain.models

import com.example.musicapp.data.jah.JahModel

data class JahDomain(
    val avatar: JahModel,
    val createdAt: String,
    val description: String,
    val file: JahModel,
    val objectId: String,
    val title: String,
    val updatedAt: String
) {
    companion object {
        val unknown = JahDomain(
            avatar = JahModel.unknown,
            createdAt = String(),
            description = String(),
            file = JahModel.unknown,
            objectId = String(),
            title = String(),
            updatedAt = String()
        )
    }
}