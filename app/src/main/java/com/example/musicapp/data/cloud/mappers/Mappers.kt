package com.example.musicapp.data.cloud.mappers

import com.example.musicapp.data.cloud.daily_mix.DailyMixCloud
import com.example.musicapp.data.cloud.daily_mix.DailyMixResponse
import com.example.musicapp.domain.models.DailyDomain


fun DailyMixCloud.toDomain(): DailyDomain = this.run {
    DailyDomain(
        avatar = avatar,
        createdAt = createdAt,
        description = description,
        file = file,
        objectId = objectId,
        title = title,
        updatedAt = updatedAt
    )
}

fun DailyDomain.toCloud(): DailyMixCloud = this.run {
    DailyMixCloud(
        avatar = avatar,
        createdAt = createdAt,
        description = description,
        file = file,
        objectId = objectId,
        title = title,
        updatedAt = updatedAt,
    )
}

