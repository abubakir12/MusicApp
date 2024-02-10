package com.example.musicapp.presentation.screen.home

sealed class UiEvents {

    data object PlayPause : UiEvents()
    data object SeekToNext : UiEvents()
    data object Backward : UiEvents()
    data object Forward : UiEvents()
    data object CategoryVisibilityChange : UiEvents()
    data class SelectedMusicChange(val index: Int) : UiEvents()
    data class SeekTo(val position: Float) : UiEvents()
    data class UpdateProgress(val newProgress: Float) : UiEvents()
    data class SearchVisibilityChange(val isVisibleChange: Boolean) : UiEvents()
}