package com.example.musicapp.data.cloud.source

import android.util.Log
import com.example.musicapp.data.cloud.daily_mix.DailyMixCloud
import com.example.musicapp.data.cloud.daily_mix.DailyMixResponse
import com.example.musicapp.data.cloud.mappers.toDomain
import com.example.musicapp.data.cloud.service.MusicService
import com.example.musicapp.domain.models.DailyDomain
import com.example.musicapp.domain.models.JahDomain
import com.example.musicapp.domain.models.MacanDomain
import com.example.musicapp.domain.models.MiyagiDomain
import com.example.musicapp.domain.models.XchoDomain
import java.util.concurrent.CancellationException

class MusicCloudDataSourceImpl(
    private val musicService: MusicService
) : MusicCloudDataSource {
    override suspend fun macanMusic(): List<MacanDomain> {
        TODO("Not yet implemented")
    }

    override suspend fun xchoMusic(): List<XchoDomain> {
        TODO("Not yet implemented")
    }

    override suspend fun miyagiMusic(): List<MiyagiDomain> {
        TODO("Not yet implemented")
    }

    override suspend fun jahMusic(): List<JahDomain> {
        TODO("Not yet implemented")
    }

    override suspend fun searchByQuery(query: String): List<DailyMixCloud> {
        return try {
            val musicResponse = musicService.searchByQuery(query)
            if (musicResponse.isSuccessful) {
                musicResponse.body()?.results ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Throwable) {
            emptyList()
        }
    }

    override suspend fun fetchAllMusic(): List<DailyDomain> {
        return try {
            val allMusics = musicService.fetchAllMusic()
            if (allMusics.isSuccessful) allMusics.body()?.results?.map { it.toDomain() }
                ?: emptyList()
            else emptyList()
        } catch (e: Throwable) {
            emptyList()
        }
    }

    override suspend fun fetchMusicById(musicId: String): List<DailyMixCloud> {
        return try {
            val musicResponse = musicService.fetchMusicById(musicId)
            if (musicResponse.isSuccessful) musicResponse.body()?.results ?: emptyList()
            Log.i("AAA","musicResponse = ${musicResponse.body()?.results}")
            emptyList()
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            emptyList()
        }
    }
}