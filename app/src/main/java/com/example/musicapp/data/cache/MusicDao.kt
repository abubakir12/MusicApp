package com.example.musicapp.data.cache

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.Query
import com.example.musicapp.data.cloud.daily_mix.DailyMixCloud
import com.example.musicapp.data.cloud.daily_mix.DailyMixResponse
import com.example.musicapp.data.models.MusicDailyCache
import com.example.musicapp.domain.models.DailyDomain
import kotlinx.coroutines.flow.Flow

@Dao
interface MusicDao {
    @Insert
    suspend fun addNewMusic(music: MusicDailyCache)

    @Query("DELETE FROM music_table WHERE id =:musicId")
    suspend fun deleteMusicById(musicId: Int)

    @Query("SELECT * FROM music_table")
    fun fetchAllSavedMusic(): Flow<List<MusicDailyCache>>

    @Query("SELECT EXISTS (SELECT * FROM music_table WHERE id = :musicId)")
    fun isMusicSave(musicId: String): Flow<Boolean>
}