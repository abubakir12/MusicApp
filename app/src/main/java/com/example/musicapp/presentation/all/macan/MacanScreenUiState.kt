package com.example.musicapp.presentation.all.macan

import com.example.musicapp.presentation.model.Macan

sealed class MacanScreenUiState{

    data object Loading:MacanScreenUiState()

    data class Loaded(
        val macan: List<Macan>
    ):MacanScreenUiState()

    data class Error(
        val error:String
    ):MacanScreenUiState()
}
