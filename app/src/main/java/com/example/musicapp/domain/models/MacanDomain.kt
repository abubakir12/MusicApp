package com.example.musicapp.domain.models

import com.example.musicapp.data.macan.MacanModel

data class MacanDomain(
    val avatar: MacanModel,
    val createdAt: String,
    val description: String,
    val file: MacanModel,
    val objectId: String,
    val title: String,
    val updatedAt: String
) {
    companion object {
        val unknown = MacanDomain(
            avatar = MacanModel.unknown,
            createdAt = String(),
            description = String(),
            file = MacanModel.unknown,
            objectId = String(),
            title = String(),
            updatedAt = String()
        )
    }
}