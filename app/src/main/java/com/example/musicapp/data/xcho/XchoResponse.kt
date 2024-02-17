package com.example.musicapp.data.xcho


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class XchoResponse(
    @SerializedName("results")
    val results: List<XchoCloud>
) : Serializable