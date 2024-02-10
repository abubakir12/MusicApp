package com.example.musicapp.domain.usecases

import com.example.musicapp.data.models.MusicDailyCache
import com.example.musicapp.domain.models.DailyDomain
import com.example.musicapp.domain.repository.MusicRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface FetchAllSavedMusicUseCases {
    operator fun invoke(): Flow<List<MusicDailyCache>>
}

class FetchAllSavedMusicUseCasesImpl @Inject constructor(
    private val musicRepository: MusicRepository
):FetchAllSavedMusicUseCases {
    override fun invoke(): Flow<List<MusicDailyCache>> {
        return musicRepository.fetchAllSavedMusic()
    }
}