package com.example.musicapp.domain.usecases

import com.example.musicapp.domain.repository.MusicRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsSavedMusicUseCasesImpl @Inject constructor(
    private val repository: MusicRepository
) : IsSavedMusicUseCases {
    override fun invoke(musicId: String): Flow<Boolean> {
        return repository.isMusicSavedFlow(musicId)
    }
}