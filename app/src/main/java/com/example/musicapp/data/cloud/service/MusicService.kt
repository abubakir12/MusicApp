package com.example.musicapp.data.cloud.service

import com.example.musicapp.data.cloud.daily_mix.DailyMixResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MusicService {

    @GET("Daily_Mix")
    suspend fun fetchAllMusic(): Response<DailyMixResponse>

    @GET("search/music")
    suspend fun searchByQuery(
        @Query("query") query: String
    ): Response<DailyMixResponse>

    @GET("Daily_Mix")
    suspend fun fetchMusicById(
        @Query("where") musicId: String,
    ): Response<DailyMixResponse>
}