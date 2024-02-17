package com.example.musicapp.domain.models

import com.example.musicapp.data.cloud.daily_mix.MusicFileModel
import java.util.UUID

data class DailyDomain(
    val avatar: MusicFileModel,
    val createdAt: String,
    val description: String,
    val file: MusicFileModel,
    val objectId: String,
    val title: String,
    val updatedAt: String
) {
    companion object {
        val unknown = DailyDomain(
            avatar = MusicFileModel.unknown,
            createdAt = "",
            description = String(),
            file = MusicFileModel.unknown,
            objectId = String(),
            title = String(),
            updatedAt = ""
        )
    }
}