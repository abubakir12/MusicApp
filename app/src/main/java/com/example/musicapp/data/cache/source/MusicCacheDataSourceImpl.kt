package com.example.musicapp.data.cache.source

import com.example.musicapp.data.cache.MusicDao
import com.example.musicapp.data.cloud.daily_mix.DailyMixCloud
import com.example.musicapp.data.models.MusicDailyCache
import kotlinx.coroutines.flow.Flow

class MusicCacheDataSourceImpl(
    private val musicDao: MusicDao
): MusicCacheDataSource {

    override suspend fun addNewMusic(music: MusicDailyCache) {
        musicDao.addNewMusic(music)
    }

    override suspend fun deleteMusicById(musicId: Int) {
        musicDao.deleteMusicById(musicId)
    }

    override fun fetchAllSavedMusic(): Flow<List<MusicDailyCache>> {
        return musicDao.fetchAllSavedMusic()
    }

    override fun isMusicSavedFlow(musicId: String): Flow<Boolean> {
        return musicDao.isMusicSave(musicId)
    }
}
