package com.example.musicapp.domain.usecases

import com.example.musicapp.domain.models.DailyDomain

interface FetchAllUseCase {
    suspend operator fun invoke(): List<DailyDomain>
}