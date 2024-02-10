package com.example.musicapp.player.service

import android.content.Intent
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import com.example.musicapp.player.notification.AudioNotificationManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AudioService @Inject constructor(
    private val mediaSession: MediaSession,
    private val notificationManager: AudioNotificationManager,
) : MediaSessionService() {

    @UnstableApi
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        notificationManager.startAudioNotificationServices(
            mediaSession = mediaSession,
            mediaSessionService = this
        )
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession {
        return mediaSession
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaSession.apply {
            release()
            if (player.playbackState != Player.STATE_IDLE) {
                player.seekTo(0L)
                player.playWhenReady = false
                player.stop()
            }

        }
    }
}