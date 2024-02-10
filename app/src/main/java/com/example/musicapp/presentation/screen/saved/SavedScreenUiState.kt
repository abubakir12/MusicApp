package com.example.musicapp.presentation.screen.saved

import com.example.musicapp.data.models.MusicDailyCache
import com.example.musicapp.domain.models.DailyDomain

data class SavedScreenUiState(
    val savedList: List<MusicDailyCache> = emptyList()
)