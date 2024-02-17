package com.example.musicapp.presentation.model

import com.example.musicapp.data.jah.JahModel
import com.example.musicapp.domain.models.JahDomain

data class Jah(
    val avatar: JahModel,
    val createdAt: String,
    val description: String,
    val file: JahModel,
    val objectId: JahModel,
    val title: String,
    val updatedAt: String
) {
    companion object {
        val unknown = Jah(
            avatar = JahModel.unknown,
            createdAt = String(),
            description = String(),
            file = JahModel.unknown,
            objectId = JahModel.unknown,
            title = String(),
            updatedAt = String()
        )
    }
}


fun JahDomain.toJah() = this.run {
    if (this == JahDomain.unknown) return@run Jah.unknown
    Jah(
        avatar = avatar,
        createdAt = createdAt,
        description = description,
        file = file,
        objectId = JahModel.unknown,
        title = title,
        updatedAt = updatedAt
    )
}