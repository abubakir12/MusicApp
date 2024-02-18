package com.example.musicapp.domain.usecases

import com.example.musicapp.domain.common.Result
import com.example.musicapp.domain.models.DailyDomain
import com.example.musicapp.domain.repository.MusicRepository
import javax.inject.Inject


class FetchMusicByIdUseCasesImpl @Inject constructor(
    private val repository: MusicRepository
) : FetchMusicByIdUseCases {

    override suspend fun fetchMusicById(musicId: String): DailyDomain {
        return repository.fetchMusicById(musicId)
    }
}
