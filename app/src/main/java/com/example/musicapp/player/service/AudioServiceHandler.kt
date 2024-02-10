package com.example.musicapp.player.service

import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AudioServiceHandler @Inject constructor(
    private val player: ExoPlayer,
) : Player.Listener {

    init {
        player.addListener(this)
    }

    private val _audioState = MutableStateFlow<AudioState>(AudioState.Initial)
    val audioState = _audioState.asStateFlow()

    private var job: Job? = null

    override fun onPlaybackStateChanged(playbackState: Int) {
        _audioState.value = when (playbackState) {
            ExoPlayer.STATE_BUFFERING -> AudioState.Buffering(player.contentPosition)
            ExoPlayer.STATE_READY -> AudioState.Ready(player.duration)
            else -> return
        }
    }

    @DelicateCoroutinesApi
    override fun onIsPlayingChanged(isPlaying: Boolean) {
        _audioState.value = AudioState.Playing(isPlaying)
        _audioState.value = AudioState.CurrentPlaying(player.currentMediaItemIndex)
        if (isPlaying) GlobalScope.launch(Dispatchers.Main) {
            startProgressUpdate()
        } else {
            stopProgressUpdate()
        }
    }

    fun addAudioItem(mediaItem: MediaItem) {
        player.setMediaItem(mediaItem)
        player.prepare()
    }

    fun setAudioItemList(mediaItems: List<MediaItem>) {
        player.setMediaItems(mediaItems)
        player.prepare()
    }

    suspend fun onPlayerEvents(
        musicPlayerEvent: AudioPlayerEvent,
        selectedMusicIndex: Int = DEFAULT_INT,
        seekToPosition: Long = 0L
    ) {
        when (musicPlayerEvent) {
            AudioPlayerEvent.Backward -> player.seekBack()
            AudioPlayerEvent.Forward -> player.seekForward()
            AudioPlayerEvent.SeekToNext -> player.seekToNext()
            AudioPlayerEvent.PlayPause -> playOrPause()
            AudioPlayerEvent.SeekTo -> player.seekTo(seekToPosition)
            AudioPlayerEvent.Stop -> stopProgressUpdate()
            is AudioPlayerEvent.UpdateProgress -> player.seekTo((player.duration * musicPlayerEvent.newProgress).toLong())
            AudioPlayerEvent.SelectedAudioChange -> {
                when (selectedMusicIndex) {
                    player.currentMediaItemIndex -> playOrPause()
                    else -> {
                        player.seekToDefaultPosition(selectedMusicIndex)
                        _audioState.value = AudioState.Playing(isPlaying = true)
                        player.playWhenReady = true
                        startProgressUpdate()
                    }
                }
            }
        }
    }

    private suspend fun playOrPause() {
        if (player.isPlaying) {
            player.pause()
            stopProgressUpdate()
        } else {
            player.play()
            _audioState.value = AudioState.Playing(isPlaying = true)
            startProgressUpdate()
        }
    }

    private suspend fun startProgressUpdate() = job.run {
        while (true) {
            delay(DELAY_TIME_MILLIS)
            _audioState.value = AudioState.Progress(player.currentPosition)
        }
    }

    private fun stopProgressUpdate() {
        job?.cancel()
        _audioState.value = AudioState.Playing(isPlaying = false)
    }

    companion object {
        private const val DEFAULT_INT = -1
        private const val DELAY_TIME_MILLIS = 500L
    }
}