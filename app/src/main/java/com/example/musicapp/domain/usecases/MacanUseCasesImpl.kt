package com.example.musicapp.domain.usecases

import com.example.musicapp.domain.models.MacanDomain
import com.example.musicapp.domain.repository.MusicRepository
import javax.inject.Inject

class MacanUseCasesImpl @Inject constructor(
    private val repository: MusicRepository
):MacanUseCases {
    override suspend fun invoke(): List<MacanDomain> = repository.macanMusic()
}