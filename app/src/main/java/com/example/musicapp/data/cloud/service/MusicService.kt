package com.example.musicapp.data.cloud.service

import com.example.musicapp.data.cloud.daily_mix.DailyMixResponse
import com.example.musicapp.data.jah.JahCloud
import com.example.musicapp.data.macan.MacanCloud
import com.example.musicapp.data.miyagi.MiyagiCloud
import com.example.musicapp.data.xcho.XchoCloud
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicService {

    @GET("Miyagi")
    suspend fun miyagiMusic(): Response<MiyagiCloud>

    @GET("Xcho")
    suspend fun xchoMusic(): Response<XchoCloud>

    @GET("macan")
    suspend fun macanMusic(): Response<MacanCloud>

    @GET("Jah_Halib")
    suspend fun jahMusic(): Response<JahCloud>

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

    @GET("Daily_Mix")
    suspend fun fetchMusicByIdMusic(
        @Query("where") params: String
    ): Response<DailyMixResponse>
}