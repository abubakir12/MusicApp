package com.example.musicapp.presentation.all.miyagi

import com.example.musicapp.presentation.all.macan.MacanScreenUiState
import com.example.musicapp.presentation.model.Miyagi

sealed class MiyagiScreenUiState {


    data object Loading: MiyagiScreenUiState()

    data class Loaded(
        val miyagi: List<Miyagi>
    ): MiyagiScreenUiState()

    data class Error(
        val error:String
    ): MiyagiScreenUiState()
}