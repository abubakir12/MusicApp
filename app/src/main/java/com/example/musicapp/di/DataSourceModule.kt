package com.example.musicapp.di

import com.example.musicapp.data.cache.MusicDao
import com.example.musicapp.data.cache.source.MusicCacheDataSource
import com.example.musicapp.data.cache.source.MusicCacheDataSourceImpl
import com.example.musicapp.data.cloud.service.MusicService
import com.example.musicapp.data.cloud.source.MusicCloudDataSource
import com.example.musicapp.data.cloud.source.MusicCloudDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    fun provideMusicCacheDataSource(
        musicDao: MusicDao
    ): MusicCacheDataSource = MusicCacheDataSourceImpl(musicDao)

    @Provides
    fun provideMusicCloudDataSource(
        musicService: MusicService
    ): MusicCloudDataSource = MusicCloudDataSourceImpl(musicService)

}