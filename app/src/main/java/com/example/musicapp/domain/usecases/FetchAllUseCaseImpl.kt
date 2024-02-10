package com.example.musicapp.domain.usecases

import com.example.musicapp.data.cloud.daily_mix.DailyMixResponse
import com.example.musicapp.domain.models.DailyDomain
import com.example.musicapp.domain.repository.MusicRepository
import javax.inject.Inject

class FetchAllUseCaseImpl @Inject constructor(
    private val repository: MusicRepository
) : FetchAllUseCase {
    override suspend fun invoke(): List<DailyDomain> =
        repository.fetchAllMusic()
}