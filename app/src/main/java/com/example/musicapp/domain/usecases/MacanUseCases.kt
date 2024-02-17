package com.example.musicapp.domain.usecases

import com.example.musicapp.domain.models.MacanDomain

interface MacanUseCases {
    suspend operator fun invoke():List<MacanDomain>
}