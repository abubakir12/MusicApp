package com.example.musicapp.presentation.screen.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.domain.models.DailyDomain
import com.example.musicapp.domain.usecases.FetchMusicByIdUseCases
import com.example.musicapp.domain.usecases.IsSavedMusicUseCases
import com.example.musicapp.domain.usecases.MusicOperatorUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
    private val fetchMusicByIdUseCases: FetchMusicByIdUseCases
) : ViewModel() {

    private val _uiStateFlow = MutableStateFlow<DetailsScreenUiState>(DetailsScreenUiState.Loading)
    val uiStateFlow: StateFlow<DetailsScreenUiState> = _uiStateFlow.asStateFlow()



    fun init(musicId: String) {
        fetchAllMusic(musicId)
    }

    private fun fetchAllMusic(musicId: String) {
        viewModelScope.launch {
            _uiStateFlow.tryEmit(DetailsScreenUiState.Loading)
            val music = fetchMusicByIdUseCases.fetchMusicById(musicId)
            Log.i("AAA","music = ${music.data}")
            if (music == null) {
                _uiStateFlow.tryEmit(DetailsScreenUiState.Error("Something went wrong"))
            } else {
                _uiStateFlow.tryEmit(
                    DetailsScreenUiState.Loaded(music = music.data ?: DailyDomain.unknown)
                )
            }
        }
    }
}