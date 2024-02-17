package com.example.musicapp.domain.usecases

import com.example.musicapp.domain.models.MiyagiDomain

interface MiyagiUseCases {

    suspend operator fun invoke():List<MiyagiDomain>
}