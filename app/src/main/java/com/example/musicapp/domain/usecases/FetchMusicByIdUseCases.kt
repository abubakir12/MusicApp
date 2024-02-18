package com.example.musicapp.domain.usecases

import com.example.musicapp.domain.common.Result
import com.example.musicapp.domain.models.DailyDomain

interface FetchMusicByIdUseCases {

    suspend fun fetchMusicById(musicId: String): DailyDomain
}