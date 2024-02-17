package com.example.musicapp.presentation.screen.home

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import com.example.musicapp.domain.models.DailyDomain
import com.example.musicapp.domain.usecases.FetchAllUseCase
import com.example.musicapp.player.service.AudioPlayerEvent
import com.example.musicapp.player.service.AudioServiceHandler
import com.example.musicapp.player.service.AudioState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(SavedStateHandleSaveableApi::class)
@SuppressLint("SuspiciousIndentation")
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchAllUseCase: FetchAllUseCase,
    private val audioServiceHandler: AudioServiceHandler,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var duration by savedStateHandle.saveable { mutableLongStateOf(0L) }
    private var mediaItemIndex by savedStateHandle.saveable { mutableIntStateOf(0) }

    var progress by savedStateHandle.saveable { mutableFloatStateOf(0f) }

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying = _isPlaying.asStateFlow()

    private val _isVisibleSearch = MutableStateFlow(value = false)
    val isVisibleSearch = _isVisibleSearch.asStateFlow()

    private var _isVisibleCategory = MutableStateFlow(value = false)
    var isVisibleCategory = _isVisibleCategory.asStateFlow()

    private val _playingMusic = MutableStateFlow(value = DailyDomain.unknown)
    val playingMusic = _playingMusic.asStateFlow()

    private val _isSaved = MutableStateFlow(value = false)
    val isSaved = _isSaved.asStateFlow()

    private val _musics = MutableStateFlow(emptyList<DailyDomain>())
    val musics = _musics.asStateFlow()

    init {
        loadMusicData()
    }

    init {
        viewModelScope.launch {
            audioServiceHandler.audioState.collectLatest {
                when (it) {
                    is AudioState.Initial -> Unit
                    is AudioState.Buffering -> calculateProgressValue(it.progress)
                    is AudioState.Playing -> _isPlaying.tryEmit(it.isPlaying)
                    is AudioState.Progress -> calculateProgressValue(it.progress)
                    is AudioState.CurrentPlaying -> _playingMusic.tryEmit(_musics.value[it.mediaItemIndex])
                    is AudioState.Ready -> duration = it.durationLong
                }
            }
        }
    }

    fun onUiEvents(uiEvents: UiEvents) = viewModelScope.launch {
        when (uiEvents) {
            UiEvents.PlayPause -> audioServiceHandler.onPlayerEvents(AudioPlayerEvent.PlayPause)
            UiEvents.SeekToNext -> audioServiceHandler.onPlayerEvents(AudioPlayerEvent.SeekToNext)
            UiEvents.Backward -> audioServiceHandler.onPlayerEvents(AudioPlayerEvent.Backward)
            UiEvents.Forward -> audioServiceHandler.onPlayerEvents(AudioPlayerEvent.Forward)
            UiEvents.CategoryVisibilityChange -> onCategoryVisibilityChange()
            is UiEvents.SearchVisibilityChange -> _isVisibleSearch.tryEmit(uiEvents.isVisibleChange)
            is UiEvents.SelectedMusicChange -> audioServiceHandler.onPlayerEvents(
                AudioPlayerEvent.SelectedAudioChange, selectedMusicIndex = uiEvents.index
            )

            is UiEvents.SeekTo -> audioServiceHandler.onPlayerEvents(
                AudioPlayerEvent.SeekTo,
                seekToPosition = ((duration / uiEvents.position) / 100f).toLong()
            )

            is UiEvents.UpdateProgress -> audioServiceHandler.onPlayerEvents(
                AudioPlayerEvent.UpdateProgress(newProgress = uiEvents.newProgress)
            )
        }
    }

    private fun onCategoryVisibilityChange() {
        _isVisibleCategory.tryEmit(!_isVisibleCategory.value)
    }

    private fun loadMusicData() {
        viewModelScope.launch {
            val result = fetchAllUseCase.invoke()

            _musics.tryEmit(
                result
            )

            if (_musics.value.isNotEmpty()) _playingMusic.tryEmit(_musics.value[0])
            setMediaItems(_musics.value)
        }
    }
    private fun setMediaItems(musics: List<DailyDomain>) {
        musics.map { music ->
            MediaItem.Builder().setUri(music.file.url).setMediaMetadata(
                MediaMetadata.Builder().setDisplayTitle(music.title)
                    .build()
            ).build()
        }.also(audioServiceHandler::setAudioItemList)
    }
    private fun calculateProgressValue(currentProgress: Long) {
        progress = if (currentProgress <= 0) 0f
        else (currentProgress.toFloat() / duration.toFloat() * 100f)
    }
    companion object {
        const val ADD_PLAYLIST_ID = "ADD_PLAYLIST_ID"
        private const val ADD_PLAYLIST_NAME = "Add playlist"
    }
}
