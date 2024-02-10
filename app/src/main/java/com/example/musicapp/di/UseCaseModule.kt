package com.example.musicapp.di

import com.example.musicapp.domain.usecases.FetchAllUseCase
import com.example.musicapp.domain.usecases.FetchAllUseCaseImpl
import com.example.musicapp.domain.usecases.FetchMusicByIdUseCases
import com.example.musicapp.domain.usecases.FetchMusicByIdUseCasesImpl
import com.example.musicapp.domain.usecases.IsSavedMusicUseCases
import com.example.musicapp.domain.usecases.IsSavedMusicUseCasesImpl
import com.example.musicapp.domain.usecases.MusicOperatorUseCases
import com.example.musicapp.domain.usecases.MusicOperatorUseCasesImpl
import com.example.musicapp.domain.usecases.SearchByQueryUseCases
import com.example.musicapp.domain.usecases.SearchByQueryUseCasesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface UseCaseModule {

    @Binds
    fun provideFetchAllUseCase(
        impl: FetchAllUseCaseImpl,
    ): FetchAllUseCase

    @Binds
    fun provideSearchByQueryUseCases(
        impl: SearchByQueryUseCasesImpl
    ): SearchByQueryUseCases

    @Binds
    fun bindIsMovieSavedUseCaseImpl(
        implement: IsSavedMusicUseCasesImpl
    ): IsSavedMusicUseCases

    @Binds
    fun bindIsFetchMusicByIdUseCasesImpl(
        implement: FetchMusicByIdUseCasesImpl
    ): FetchMusicByIdUseCases

    @Binds
    fun bindMusicOperatorUseCases(
        impl: MusicOperatorUseCasesImpl
    ): MusicOperatorUseCases

}
