package com.example.musicapp.domain.usecases

import com.example.musicapp.domain.models.MiyagiDomain
import com.example.musicapp.domain.repository.MusicRepository
import javax.inject.Inject

class MiyagiUseCasesImpl @Inject constructor(
    private val repository: MusicRepository
):MiyagiUseCases {
    override suspend fun invoke(): List<MiyagiDomain> = repository.miyagiMusic()
}