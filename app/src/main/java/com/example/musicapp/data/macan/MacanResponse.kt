package com.example.musicapp.data.macan


import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class MacanResponse(
    @SerializedName("results") val results: List<MacanCloud>
) : Serializable