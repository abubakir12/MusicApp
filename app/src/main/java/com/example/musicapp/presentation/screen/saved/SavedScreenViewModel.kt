package com.example.musicapp.presentation.screen.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.domain.usecases.FetchAllSavedMusicUseCasesImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SavedScreenViewModel @Inject constructor(
    private val fetchAllSavedMusicUseCases: FetchAllSavedMusicUseCasesImpl
) : ViewModel() {

    private val _uiStateFlow =
        MutableStateFlow(SavedScreenUiState())
    val uiStateFlow: StateFlow<SavedScreenUiState> = _uiStateFlow.asStateFlow()


    fun fetchAllSavedMusic() {
        fetchAllSavedMusicUseCases().onEach {}
            .onEach { music ->
                _uiStateFlow.tryEmit(SavedScreenUiState(music))
            }.launchIn(viewModelScope)

    }
}