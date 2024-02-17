package com.example.musicapp.data.jah


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class JahResponse(
    @SerializedName("results")
    val jahResponses: List<JahCloud>
) : Serializable