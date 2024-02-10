package com.example.musicapp.domain.models

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.UUID


data class ReviewsDomain(
    val reviewsDetails: ReviewsDetailsDomain,
    val name: String,
    val id: String,
    val description: String
) {
    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        val unknown = ReviewsDomain(
            name = "Abu",
            description = "",
            id = UUID.randomUUID().toString(),
            reviewsDetails = ReviewsDetailsDomain.unknown,
        )
    }
}