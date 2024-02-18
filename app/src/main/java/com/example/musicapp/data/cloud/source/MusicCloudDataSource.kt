package com.example.musicapp.data.cloud.source

import com.example.musicapp.data.cloud.daily_mix.DailyMixCloud
import com.example.musicapp.domain.models.DailyDomain
import com.example.musicapp.domain.models.JahDomain
import com.example.musicapp.domain.models.MacanDomain
import com.example.musicapp.domain.models.MiyagiDomain
import com.example.musicapp.domain.models.XchoDomain

interface MusicCloudDataSource {

    suspend fun macanMusic(): List<MacanDomain>

    suspend fun xchoMusic():List<XchoDomain>
    suspend fun miyagiMusic():List<MiyagiDomain>
    suspend fun jahMusic():List<JahDomain>
    suspend fun searchByQuery(query: String): List<DailyMixCloud>
    suspend fun fetchAllMusic(): List<DailyDomain>
}