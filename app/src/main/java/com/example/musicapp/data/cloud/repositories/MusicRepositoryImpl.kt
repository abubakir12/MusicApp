package com.example.musicapp.data.cloud.repositories

import android.util.Log
import com.example.musicapp.data.cache.source.MusicCacheDataSource
import com.example.musicapp.data.cloud.daily_mix.DailyMixCloud
import com.example.musicapp.data.cloud.daily_mix.DailyMixResponse
import com.example.musicapp.data.cloud.mappers.toDomain
import com.example.musicapp.data.cloud.source.MusicCloudDataSource
import com.example.musicapp.data.models.MusicDailyCache
import com.example.musicapp.domain.common.Result
import com.example.musicapp.domain.models.DailyDomain
import com.example.musicapp.domain.repository.MusicRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okhttp3.internal.notifyAll
import java.util.concurrent.CancellationException
import javax.inject.Inject

class MusicRepositoryImpl @Inject constructor(
    private val musicCloudDataSource: MusicCloudDataSource,
    private val musicCacheDataSource: MusicCacheDataSource,
) : MusicRepository {
    override fun isMusicSavedFlow(musicId: String): Flow<Boolean> {
        return musicCacheDataSource.isMusicSavedFlow(musicId)
    }

    override suspend fun fetchAllMusic(): List<DailyDomain> {
        return musicCloudDataSource.fetchAllMusic()
    }

    override fun fetchAllSavedMusic(): Flow<List<MusicDailyCache>> {
        return musicCacheDataSource.fetchAllSavedMusic()
    }

    override suspend fun searchByQuery(query: String): List<DailyDomain> {
        return musicCloudDataSource.searchByQuery(query).map { it.toDomain() }
    }

    override suspend fun fetchMusicById(musicId: String): Result<DailyDomain> {
        return try {
            val params = "{\"objectId\":\"$musicId\"}"
            val response = musicCloudDataSource.fetchMusicById(params)
            val music = response.firstOrNull()?.toDomain() ?: DailyDomain.unknown
            Result.Success(data = music)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Result.Error("Something went wrong")
        }
    }
}