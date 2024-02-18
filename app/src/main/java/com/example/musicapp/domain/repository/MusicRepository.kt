package com.example.musicapp.domain.repository

import com.example.musicapp.data.cloud.daily_mix.DailyMixCloud
import com.example.musicapp.data.cloud.daily_mix.DailyMixResponse
import com.example.musicapp.data.models.MusicDailyCache
import com.example.musicapp.domain.common.Result
import com.example.musicapp.domain.models.DailyDomain
import com.example.musicapp.domain.models.JahDomain
import com.example.musicapp.domain.models.MacanDomain
import com.example.musicapp.domain.models.MiyagiDomain
import com.example.musicapp.domain.models.XchoDomain
import kotlinx.coroutines.flow.Flow

interface MusicRepository {

    fun isMusicSavedFlow(musicId: String): Flow<Boolean>
    suspend fun fetchAllMusic(): List<DailyDomain>

    suspend fun macanMusic(): List<MacanDomain>

    suspend fun xchoMusic(): List<XchoDomain>
    suspend fun miyagiMusic(): List<MiyagiDomain>
    suspend fun jahMusic(): List<JahDomain>
    fun fetchAllSavedMusic(): Flow<List<MusicDailyCache>>
    suspend fun searchByQuery(query: String): List<DailyDomain>
    suspend fun fetchMusicById(musicId: String): DailyDomain
}