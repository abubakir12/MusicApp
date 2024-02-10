package com.example.musicapp.di

import com.example.musicapp.data.cache.source.MusicCacheDataSource
import com.example.musicapp.data.cloud.repositories.MusicRepositoryImpl
import com.example.musicapp.data.cloud.source.MusicCloudDataSource
import com.example.musicapp.domain.repository.MusicRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class RepositoriesModule {

    @Provides
    fun provideMusicRepository(
        cloudDataSource: MusicCloudDataSource, cacheDataSource: MusicCacheDataSource
    ):MusicRepository = MusicRepositoryImpl(
        musicCacheDataSource = cacheDataSource, musicCloudDataSource = cloudDataSource
    )


}
