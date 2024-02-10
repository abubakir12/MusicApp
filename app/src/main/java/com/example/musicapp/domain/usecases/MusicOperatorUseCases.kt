package com.example.musicapp.domain.usecases

import com.example.musicapp.domain.models.DailyDomain
import com.example.musicapp.domain.repository.MusicRepository
import javax.inject.Inject

interface MusicOperatorUseCases {

    suspend fun savedMusic(music: DailyDomain)
}


class MusicOperatorUseCasesImpl @Inject constructor(
    private val repository: MusicRepository
) : MusicOperatorUseCases {
    override suspend fun savedMusic(music: DailyDomain) {
        repository.fetchAllMusic()
    }
}