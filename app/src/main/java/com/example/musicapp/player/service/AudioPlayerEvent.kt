package com.example.musicapp.player.service

sealed class AudioPlayerEvent {

    data object PlayPause : AudioPlayerEvent()
    data object SelectedAudioChange : AudioPlayerEvent()
    data object Backward : AudioPlayerEvent()
    data object SeekToNext : AudioPlayerEvent()
    data object Forward : AudioPlayerEvent()
    data object SeekTo : AudioPlayerEvent()
    data object Stop : AudioPlayerEvent()
    data class UpdateProgress(val newProgress: Float) : AudioPlayerEvent()

}