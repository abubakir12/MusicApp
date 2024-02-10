package com.example.musicapp.player.service

sealed class AudioState {

    data object Initial : AudioState()
    data class Ready(val durationLong: Long) : AudioState()
    data class Progress(val progress: Long) : AudioState()
    data class Buffering(val progress: Long) : AudioState()
    data class Playing(val isPlaying: Boolean) : AudioState()
    data class CurrentPlaying(val mediaItemIndex: Int) : AudioState()
}