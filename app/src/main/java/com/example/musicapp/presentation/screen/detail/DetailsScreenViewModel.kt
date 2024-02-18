package com.example.musicapp.presentation.screen.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.domain.usecases.FetchMusicByIdUseCases
import com.example.musicapp.player.service.AudioServiceHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.Serializable
import javax.inject.Inject


enum class ItemDetailType(
    val type: Int
) : Serializable {
    JAH(0),
    MACAN(1),
    XCHO(2),
    MIYAGI(3),
    UNKNOWN(-1);

    companion object {

        fun findByType(type: Int): ItemDetailType = entries.find { it.type == type } ?: UNKNOWN
    }
}


@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val fetchMusicByIdUseCases: FetchMusicByIdUseCases,
    private val musicServicesHandler: AudioServiceHandler,
) : ViewModel() {

    private val _uiStateFlow = MutableStateFlow<DetailsScreenUiState>(DetailsScreenUiState.Loading)
    val uiStateFlow: StateFlow<DetailsScreenUiState> = _uiStateFlow.asStateFlow()

    private val handler = CoroutineExceptionHandler { _, throwable ->
        _uiStateFlow.tryEmit(DetailsScreenUiState.Error(throwable.localizedMessage ?: ""))
    }


    fun init(musicId: String) {
        fetchMusicByIdUseCase(musicId)
    }

    private fun fetchMusicByIdUseCase(id: String) {
        viewModelScope.launch(handler + Dispatchers.IO) {
            _uiStateFlow.tryEmit(DetailsScreenUiState.Loading)
            val musicId = fetchMusicByIdUseCases.fetchMusicById(id)
            if (musicId != null) {
                Log.i("Abubakir", musicId.title)
            }
            if (musicId == null) {
                _uiStateFlow.tryEmit(DetailsScreenUiState.Error("Sorry error"))
            } else {
                _uiStateFlow.tryEmit(
                    DetailsScreenUiState.Loaded(music = musicId)
                )
            }
        }
    }

}