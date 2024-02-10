package com.example.musicapp.domain.usecases

import com.example.musicapp.data.cloud.daily_mix.DailyMixCloud
import com.example.musicapp.data.cloud.daily_mix.DailyMixResponse
import com.example.musicapp.domain.common.Result
import com.example.musicapp.domain.models.DailyDomain

interface FetchMusicByIdUseCases {

    suspend fun fetchMusicById(musicId: String): Result<DailyDomain>
}