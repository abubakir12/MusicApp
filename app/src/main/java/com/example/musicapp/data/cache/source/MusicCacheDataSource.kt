package com.example.musicapp.data.cache.source

import com.example.musicapp.data.cloud.daily_mix.DailyMixCloud
import com.example.musicapp.data.models.MusicDailyCache
import kotlinx.coroutines.flow.Flow

interface MusicCacheDataSource {

    suspend fun addNewMusic(music: MusicDailyCache)

    suspend fun deleteMusicById(musicId: Int)

    fun fetchAllSavedMusic(): Flow<List<MusicDailyCache>>

    fun isMusicSavedFlow(musicId: String): Flow<Boolean>
}