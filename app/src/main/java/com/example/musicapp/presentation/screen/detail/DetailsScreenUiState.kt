package com.example.musicapp.presentation.screen.detail

import com.example.musicapp.domain.models.DailyDomain

sealed class DetailsScreenUiState {
    data object Loading : DetailsScreenUiState()

    data class Loaded(
        val music: DailyDomain,
        val isSaved: Boolean = false,
    ) : DetailsScreenUiState()

    data class Error(
        val message: String
    ) : DetailsScreenUiState()

}