package com.example.musicapp.domain.usecases

import com.example.musicapp.domain.models.DailyDomain
import com.example.musicapp.domain.repository.MusicRepository
import javax.inject.Inject

class SearchByQueryUseCasesImpl @Inject constructor(
    private val repository: MusicRepository
):SearchByQueryUseCases {
    override suspend fun searchByQuery(query: String): List<DailyDomain> {
        return repository.searchByQuery(query)
    }
}