package com.example.musicapp.data.cloud.source

import com.example.musicapp.data.cloud.daily_mix.DailyMixCloud
import com.example.musicapp.data.cloud.daily_mix.DailyMixResponse
import com.example.musicapp.domain.models.DailyDomain

interface MusicCloudDataSource {

    suspend fun searchByQuery(query: String): List<DailyMixCloud>
    suspend fun fetchAllMusic(): List<DailyDomain>
    suspend fun fetchMusicById(musicId: String): List<DailyMixCloud>
}