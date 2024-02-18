package com.example.musicapp.presentation.screen.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.musicapp.R
import com.example.musicapp.domain.models.DailyDomain
import com.example.musicapp.presentation.theme.ExtraMediumSpacing
import com.example.musicapp.presentation.theme.ProgressTrackColor

@Composable
fun DetailScreen(
    music: () -> Unit,
    uiState: DetailsScreenUiState,
    navController: NavHostController,
) {
    LaunchedEffect(key1 = Unit) { music() }
    Scaffold { innerPaddings ->
        when (uiState) {
            is DetailsScreenUiState.Loading -> {}
            is DetailsScreenUiState.Loaded -> LoadedDetailScreen(
                navController = navController,
                modifier = Modifier.padding(innerPaddings),
                uiState = uiState,
            )

            is DetailsScreenUiState.Error -> {}
        }
    }
}

@Composable
fun LoadedDetailScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    uiState: DetailsScreenUiState.Loaded,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        DetailScreenHeader(
            navController = navController
        )
        Spacer(modifier = Modifier.weight(1f))
        DetailBackgroundContent(
            music = uiState.music
        )
        SliderScreen()
        Spacer(modifier = Modifier.weight(1f))
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenHeader(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(title = {
        Text(
            modifier = modifier,
            text = stringResource(id = R.string.daily_mix),
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.SemiBold, color = if (isSystemInDarkTheme()) Color.White
                else Color.Black
            )
        )
    }, navigationIcon = {
        Icon(
            modifier = modifier
                .size(25.dp)
                .clickable { navController.navigateUp() },
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null
        )
    })
}

@Composable
fun DetailBackgroundContent(
    music: DailyDomain,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(ExtraMediumSpacing)
            .height(350.dp)
            .fillMaxWidth(),
    ) {
        AsyncImage(
            model = music.avatar.url,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(15.dp)),
            contentScale = ContentScale.Crop
        )
        Text(
            text = music.title,
            style = MaterialTheme.typography.titleMedium.copy(
                color = Color.Black
            ),
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SliderScreen(
    modifier: Modifier = Modifier
) {
    val sliderPosition = remember { mutableFloatStateOf(value = 5f) }
    val interactionSource = remember { MutableInteractionSource() }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Slider(
            modifier = Modifier
                .padding(top = 10.dp),
            value = sliderPosition.floatValue,
            onValueChange = { sliderPosition.floatValue = it },
            valueRange = 0f..100f,
            colors = SliderDefaults.colors(
                thumbColor = ProgressTrackColor,
                activeTrackColor = ProgressTrackColor,
                activeTickColor = ProgressTrackColor,
                inactiveTickColor = ProgressTrackColor,
                inactiveTrackColor = ProgressTrackColor,
            ),
            thumb = {
                SliderDefaults.Thumb(
                    interactionSource = interactionSource,
                    thumbSize = DpSize(8.dp, 4.dp),
                    colors = SliderDefaults.colors(thumbColor = ProgressTrackColor)
                )
            },
        )
        Spacer(modifier = Modifier.height(80.dp))
        Row(
            modifier = Modifier.padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center

        ) {
            Spacer(modifier = Modifier.padding(start = 24.dp))
            Icon(
                modifier = Modifier.size(42.dp),
                painter = painterResource(id = R.drawable.back),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.padding(start = 24.dp))
            Icon(
                modifier = Modifier.size(42.dp),
                painter = painterResource(id = R.drawable.pause),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.padding(start = 24.dp))
            Icon(
                modifier = Modifier.size(42.dp),
                painter = painterResource(id = R.drawable.next),
                contentDescription = null,
            )
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