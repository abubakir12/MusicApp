package com.example.musicapp.presentation.splash

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.musicapp.R
import com.example.musicapp.presentation.theme.GrayMusic
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition", "RememberReturnType")
@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onNavigateToOnBoardingScreen: () -> Unit,
) {
    val speed by remember { mutableFloatStateOf(1f) }
    val scope = rememberCoroutineScope()
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.splash)
    )

    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
        speed = speed,
        restartOnPlay = false
    )
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(GrayMusic),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            modifier = Modifier.size(160.dp),
            composition = composition,
            progress = progress,
        )
    }
    scope.launch {
        delay(3000L)
        onNavigateToOnBoardingScreen()
    }
}