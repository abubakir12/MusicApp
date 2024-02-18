package com.example.musicapp.data.cloud.repositories

import com.example.musicapp.data.cache.source.MusicCacheDataSource
import com.example.musicapp.data.cloud.mappers.toDomain
import com.example.musicapp.data.cloud.service.MusicService
import com.example.musicapp.data.cloud.source.MusicCloudDataSource
import com.example.musicapp.data.models.MusicDailyCache
import com.example.musicapp.domain.models.DailyDomain
import com.example.musicapp.domain.models.JahDomain
import com.example.musicapp.domain.models.MacanDomain
import com.example.musicapp.domain.models.MiyagiDomain
import com.example.musicapp.domain.models.XchoDomain
import com.example.musicapp.domain.repository.MusicRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MusicRepositoryImpl @Inject constructor(
    private val musicCloudDataSource: MusicCloudDataSource,
    private val musicCacheDataSource: MusicCacheDataSource,
    private val musicService: MusicService
) : MusicRepository {
    override fun isMusicSavedFlow(musicId: String): Flow<Boolean> {
        return musicCacheDataSource.isMusicSavedFlow(musicId)
    }

    override suspend fun fetchAllMusic(): List<DailyDomain> {
        return musicCloudDataSource.fetchAllMusic()
    }

    override suspend fun macanMusic(): List<MacanDomain> {
        return musicCloudDataSource.macanMusic()
    }

    override suspend fun xchoMusic(): List<XchoDomain> {
        return musicCloudDataSource.xchoMusic()
    }

    override suspend fun miyagiMusic(): List<MiyagiDomain> {
        return musicCloudDataSource.miyagiMusic()
    }

    override suspend fun jahMusic(): List<JahDomain> {
        return musicCloudDataSource.jahMusic()
    }

    override fun fetchAllSavedMusic(): Flow<List<MusicDailyCache>> {
        return musicCacheDataSource.fetchAllSavedMusic()
    }

    override suspend fun searchByQuery(query: String): List<DailyDomain> {
        return musicCloudDataSource.searchByQuery(query).map { it.toDomain() }
    }


    override suspend fun fetchMusicById(musicId: String): DailyDomain {
        val params = "{\"objectId\":\"$musicId\"}"
        val response = musicService.fetchMusicByIdMusic(params)
        val result = response.body()
        val music = result?.results?.map { it.toDomain() }?.first()
        return music!!
    }
}