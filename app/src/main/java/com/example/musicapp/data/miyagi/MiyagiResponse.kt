package com.example.musicapp.data.miyagi


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MiyagiResponse(
    @SerializedName("results")
    val results: List<MiyagiCloud>
) : Serializable