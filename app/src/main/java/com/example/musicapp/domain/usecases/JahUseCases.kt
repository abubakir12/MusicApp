package com.example.musicapp.domain.usecases

import com.example.musicapp.domain.models.JahDomain

interface JahUseCases {

    suspend operator fun invoke():List<JahDomain>
}