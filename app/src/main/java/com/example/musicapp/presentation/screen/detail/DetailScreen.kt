package com.example.musicapp.presentation.screen.detail

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.musicapp.R
import com.example.musicapp.domain.models.DailyDomain
import com.example.musicapp.presentation.theme.Background
import kotlinx.coroutines.flow.StateFlow

@Composable
fun DetailScreen(
    fetchMusic: () -> Unit,
    uiStateFlow: StateFlow<DetailsScreenUiState>,
    navController: NavHostController,
) {
    val fullScreenModifier = Modifier.background(Background)
    val uiState = uiStateFlow.collectAsStateWithLifecycle().value
    LaunchedEffect(key1 = Unit) { fetchMusic() }

    Scaffold { innerPaddings ->
        when (uiState) {
            is DetailsScreenUiState.Loading -> LoadingDetailScreen(
                modifier = fullScreenModifier,
            )

            is DetailsScreenUiState.Loaded -> LoadedDetailScreen(
                navController = navController,
                modifier = Modifier.padding(innerPaddings),
                uiState = uiState,
            )

            else -> {}
        }
    }
}

@Composable
fun LoadedDetailScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    uiState: DetailsScreenUiState.Loaded,
) {
    val scrollState = rememberScrollState()
    var screenHeight: Dp

    BoxWithConstraints(
        modifier = modifier.fillMaxSize()
    ) {
        screenHeight = maxHeight
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(state = scrollState)
        ) {
            AsyncImage(
                modifier = Modifier.size(200.dp),
                model = uiState.music.avatar,
                contentDescription = null
            )
            Column {
                DetailScreenHeader(
                    navController = navController
                )
                Spacer(modifier = modifier.height(30.dp))
                DetailBackgroundContent(
                    isSaved = uiState.isSaved,
                    music = uiState.music
                )
                Spacer(modifier = modifier.height(16.dp))
                SliderScreen()
            }
        }
    }
}


@Composable
fun DetailScreenHeader(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(top = 16.dp)
    ) {
        Row(
            modifier = modifier.padding(horizontal = 12.dp),
        ) {
            Icon(
                modifier = modifier
                    .size(35.dp)
                    .clickable { navController.navigateUp() },
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = null,
                tint = if (isSystemInDarkTheme()) Color.White
                else Color.Black
            )
        }
    }
}

@Composable
fun DetailBackgroundContent(
    music: DailyDomain,
    isSaved: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .width(286.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp)),
            model = "music.avatar",
            contentDescription = null
        )
        Column(
            modifier = modifier.padding(top = 16.dp)
        ) {
            Row(
                modifier = modifier.padding(horizontal = 12.dp),
            ) {
                Text(
                    text = "",
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 24.sp
                )
                Spacer(modifier = modifier.weight(1f))
                Icon(
                    modifier = modifier
                        .size(35.dp),
                    painter = painterResource(
                        id = if (isSaved) R.drawable.isliked else R.drawable.not_liked
                    ),
                    contentDescription = null,
                    tint = if (isSystemInDarkTheme()) Color.White
                    else Color.Black
                )
            }
        }
    }
}

@Composable
fun SliderScreen(
    modifier: Modifier = Modifier
) {
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    Column {
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it }
        )
        Text(text = sliderPosition.toString())
    }
    Column(
        modifier = modifier.padding(top = 16.dp)
    ) {
        Row(
            modifier = modifier.padding(horizontal = 12.dp),
        ) {
            Icon(
                modifier = modifier.size(24.dp),
                painter = painterResource(id = R.drawable.group),
                contentDescription = null,
            )
            Spacer(modifier = modifier.padding(start = 24.dp))
            Icon(
                modifier = modifier.size(24.dp),
                painter = painterResource(id = R.drawable.back),
                contentDescription = null,
            )
            Spacer(modifier = modifier.padding(start = 24.dp))
            Icon(
                modifier = modifier.size(24.dp),
                painter = painterResource(id = R.drawable.pause),
                contentDescription = null,
            )
            Spacer(modifier = modifier.padding(start = 24.dp))
            Icon(
                modifier = modifier.size(24.dp),
                painter = painterResource(id = R.drawable.next),
                contentDescription = null,
            )
            Spacer(modifier = modifier.padding(start = 24.dp))
            Icon(
                modifier = modifier.size(24.dp),
                painter = painterResource(id = R.drawable.replay),
                contentDescription = null,
            )
            Spacer(modifier = modifier.padding(start = 24.dp))
        }
    }
}


@Composable
fun LoadingDetailScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier, contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}