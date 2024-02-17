package com.example.musicapp.domain.usecases

import com.example.musicapp.domain.models.XchoDomain
import com.example.musicapp.domain.repository.MusicRepository
import javax.inject.Inject

class XchoUseCasesImpl @Inject constructor(
    private val repository: MusicRepository
):XchoUseCases{
    override suspend fun invoke(): List<XchoDomain> = repository.xchoMusic()
}