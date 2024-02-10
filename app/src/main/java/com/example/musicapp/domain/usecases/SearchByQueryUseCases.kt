package com.example.musicapp.domain.usecases

import com.example.musicapp.domain.models.DailyDomain

interface SearchByQueryUseCases {
    suspend fun searchByQuery(query: String): List<DailyDomain>
}