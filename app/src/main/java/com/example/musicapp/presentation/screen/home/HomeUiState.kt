package com.example.musicapp.presentation.screen.home

import com.example.musicapp.domain.models.DailyDomain

sealed class HomeUiState {

    object Loading : HomeUiState()

    data class Loaded(
        val dailyDomain: List<DailyDomain>
    ):HomeUiState()

}