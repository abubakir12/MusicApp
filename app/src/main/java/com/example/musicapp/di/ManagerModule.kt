package com.example.musicapp.di

import com.example.musicapp.presentation.manager.Navigator
import com.example.musicapp.presentation.manager.NavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface ManagerModule {

    @Binds
    @Singleton
    fun bindNavigator(
        impl: NavigatorImpl
    ): Navigator



}