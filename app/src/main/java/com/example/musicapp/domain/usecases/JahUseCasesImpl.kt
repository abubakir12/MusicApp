package com.example.musicapp.domain.usecases

import com.example.musicapp.domain.models.JahDomain
import com.example.musicapp.domain.repository.MusicRepository
import javax.inject.Inject

class JahUseCasesImpl @Inject constructor(
    private val repository: MusicRepository
):JahUseCases {
    override suspend fun invoke(): List<JahDomain> = repository.jahMusic()
}