package com.example.musicapp.presentation.all.jah

import com.example.musicapp.presentation.model.Jah

sealed class JahScreenUiState {


    data object Loading : JahScreenUiState()

    data class Loaded(
        val jah: List<Jah>
    ) : JahScreenUiState()

    data class Error(
        val error: String
    ) : JahScreenUiState()
}