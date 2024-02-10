package com.example.musicapp.player.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.media3.session.MediaSession
import androidx.core.app.NotificationManagerCompat
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSessionService
import androidx.media3.ui.PlayerNotificationManager
import com.example.musicapp.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AudioNotificationManager @Inject constructor(
    @ApplicationContext private val context: Context, private val player: ExoPlayer
) {

    init {
        createNotificationChannel()
    }

    private val notificationManager by lazy {
        NotificationManagerCompat.from(context)
    }

    @UnstableApi
    fun startAudioNotificationServices(
        mediaSession: MediaSession,
        mediaSessionService: MediaSessionService
    ) {
        buildNotification(mediaSession)
        startForeGroundMusicNotificationService(mediaSessionService)
    }

    private fun createNotificationChannel() {
        notificationManager.createNotificationChannel(
            NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_LOW
            )
        )
    }

    private fun startForeGroundMusicNotificationService(mediaSessionService: MediaSessionService) {
        val notification = Notification.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setCategory(Notification.CATEGORY_SERVICE).build()
        mediaSessionService.startForeground(NOTIFICATION_ID, notification)
    }

    @UnstableApi
    private fun buildNotification(mediaSession: MediaSession) {
        PlayerNotificationManager.Builder(context, NOTIFICATION_ID, NOTIFICATION_CHANNEL_ID)
            .setMediaDescriptionAdapter(
                AudioNotificationAdapter(
                    context = context, pendingIntent = mediaSession.sessionActivity
                )
            ).setSmallIconResourceId(R.drawable.no_music).build().also {
//                it.setMediaSessionToken(mediaSession.sessionCompatToken)
                it.setUseFastForwardActionInCompactView(true)
                it.setUseRewindActionInCompactView(true)
                it.setUseNextActionInCompactView(true)
                it.setPriority(NotificationCompat.PRIORITY_LOW)
                it.setPlayer(player)
            }
    }

    companion object {
        private const val NOTIFICATION_ID = 323
        private const val NOTIFICATION_CHANNEL_NAME = "audio channel name $NOTIFICATION_ID"
        private const val NOTIFICATION_CHANNEL_ID = "audio channel id $NOTIFICATION_ID"
    }
}