package com.example.musicapp.domain.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.UUID

data class ReviewsDetailsDomain(
    val avatarPath: String,
    val name: String,
    val description: String
) {
    companion object {
        val unknown = ReviewsDetailsDomain(
           avatarPath = String(),
            name = "ddfs",
            description = "abu"
        )
    }
}
