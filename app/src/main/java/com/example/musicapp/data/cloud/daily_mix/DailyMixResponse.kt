package com.example.musicapp.data.cloud.daily_mix


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DailyMixResponse(
    @SerializedName("results") val results: List<DailyMixCloud>
) : Serializable