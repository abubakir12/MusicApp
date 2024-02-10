package com.example.musicapp.di

import android.content.Context
import androidx.room.Room
import com.example.musicapp.data.cache.MusicDao
import com.example.musicapp.data.cache.database.MusicDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


private const val MUSIC_DATABASE_NAME = "music_database"


@Module
@InstallIn(SingletonComponent::class)
class RoomModel {

    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): MusicDataBase = Room.databaseBuilder(
        context,
        MusicDataBase::class.java,
        MUSIC_DATABASE_NAME
    ).build()

    @Provides
    fun provideMusicDao(
        dataBase: MusicDataBase
    ): MusicDao = dataBase.musicDao()

}