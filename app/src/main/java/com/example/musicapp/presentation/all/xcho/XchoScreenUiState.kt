package com.example.musicapp.presentation.all.xcho

import com.example.musicapp.presentation.all.macan.MacanScreenUiState
import com.example.musicapp.presentation.model.Xcho

sealed class XchoScreenUiState {


    data object Loading : XchoScreenUiState()

    data class Loaded(
        val xcho: List<Xcho>
    ) : XchoScreenUiState()

    data class Error(
        val error: String
    ) : XchoScreenUiState()
}