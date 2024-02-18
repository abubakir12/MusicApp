package com.example.musicapp.presentation.screen.detail

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.musicapp.R
import com.example.musicapp.domain.models.DailyDomain
import com.example.musicapp.presentation.theme.ProgressTrackColor

@Composable
fun DetailScreen(
    music: DailyDomain,
    isSaved: Boolean,
    navController: NavHostController,
    fetchMusic: () -> Unit,
) {
    LaunchedEffect(key1 = Unit) { fetchMusic() }

    Scaffold { innerPaddings ->
//        when (uiState) {
//            is DetailsScreenUiState.Loading -> LoadingDetailScreen(
//                modifier = fullScreenModifier,
//            )

//            is DetailsScreenUiState.Loaded ->
        LoadedDetailScreen(
            navController = navController,
            modifier = Modifier.padding(innerPaddings),
            music = music,
            isSaved = isSaved
        )

//            else -> {}
    }
//    }
}

@Composable
fun LoadedDetailScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    music: DailyDomain,
    isSaved: Boolean
) {
    val scrollState = rememberScrollState()
    var screenHeight: Dp

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(state = scrollState)

    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DetailScreenHeader(
                navController = navController
            )
            Spacer(modifier = modifier.weight(2f))
            DetailBackgroundContent(
                isSaved = isSaved, music = music
            )
            Spacer(modifier = modifier.weight(2f))
            SliderScreen()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenHeader(
    navController: NavHostController, modifier: Modifier = Modifier
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
    music: DailyDomain, isSaved: Boolean, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = modifier
                .height(330.dp)
                .width(300.dp),
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(15.dp)),
                model = music.avatar.url,
                contentDescription = null
            )
            Log.i("Abu", music.title)
            Text(
                modifier = modifier.padding(top = 6.dp),
                text = music.title,
                style = MaterialTheme.typography.titleMedium,
                fontSize = 16.sp
            )
//            Icon(
//                modifier = modifier.size(35.dp), painter = painterResource(
//                    id = if (isSaved) R.drawable.isliked else R.drawable.not_liked
//                ), contentDescription = null, tint = if (isSystemInDarkTheme()) Color.White
//                else Color.Black
//            )
        }
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
        Spacer(modifier = Modifier.weight(1f))
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
            Icon(
                modifier = Modifier.size(42.dp),
                painter = painterResource(id = R.drawable.group),
                contentDescription = null,
            )
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
            Spacer(modifier = Modifier.padding(start = 24.dp))
            Icon(
                modifier = Modifier.size(42.dp),
                painter = painterResource(id = R.drawable.replay),
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
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.interaction.MutableInteractionSource
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Slider
//import androidx.compose.material3.SliderDefaults
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.mutableFloatStateOf
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.shadow
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.painter.Painter
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.unit.DpSize
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.lifecycle.compose.collectAsStateWithLifecycle
//import androidx.navigation.NavHostController
//import com.example.musicapp.R
//import com.example.musicapp.presentation.model.MusicUi
//import com.example.musicapp.presentation.screen.detail.DetailsScreenUiState
//import com.example.musicapp.presentation.theme.Background
//import com.example.musicapp.presentation.theme.ImageStyleTheme
//import com.example.musicapp.presentation.theme.MusicButtons
//import com.example.musicapp.presentation.theme.MusicProgressText
//import com.example.musicapp.presentation.theme.ProgressColor
//import com.example.musicapp.presentation.theme.ProgressTrackColor
//import com.example.musicapp.presentation.theme.TextStyleTheme
//import kotlinx.coroutines.flow.StateFlow
//
//
//@Composable
//fun DetailScreen(
//    fetchMusic: () -> Unit,
//    uiStateFlow: StateFlow<DetailsScreenUiState>,
//    navController: NavHostController,
//) {
//    val fullScreenModifier = Modifier.background(Background)
//    val uiState = uiStateFlow.collectAsStateWithLifecycle().value
//    LaunchedEffect(key1 = Unit) { fetchMusic() }
//
//    Scaffold { innerPaddings ->
//        when (uiState) {
//            is DetailsScreenUiState.Loading -> LoadingDetailScreen(
//                modifier = fullScreenModifier,
//            )
//
//            is DetailsScreenUiState.Loaded -> LoadedDetailScreen(
//                navController = navController,
//                modifier = Modifier.padding(innerPaddings),
//                uiState = uiState,
//            )
//
//            else -> {}
//        }
//    }
//}
//
//@Composable
//fun DetailScreen(
//    modifier: Modifier = Modifier,
//    music: MusicUi,
//) {
//    Row(
//        modifier = modifier
//            .fillMaxWidth()
//            .padding(horizontal = 16.dp, vertical = 24.dp)
//    ) {
//
//        MusicButtons(
//            modifier = modifier,
//            boxSize = 44.dp,
//            imageSize = 20.dp,
//            imageIcon = R.drawable.arrow_left,
//        )
//
//        Spacer(modifier = Modifier.weight(1f))
//
//        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//            Text(
//                modifier = modifier.size(16.dp),
//                text = stringResource(id = R.string.daily_mix),
//                color = Color.White
//            )
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MusicPlayerScreen(modifier: Modifier = Modifier, music: MusicUi) {
//    Column(
//        modifier = modifier.padding(horizontal = 16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//        Spacer(modifier = modifier.weight(1f))
//
//        ImageStyleTheme(
//            modifier = modifier
//                .size(180.dp)
//                .shadow(elevation = 16.dp, shape = RoundedCornerShape(32.dp)),
//            painter = painterResource(id = R.drawable.no_music)
//        )
//
//        Spacer(modifier = modifier.weight(1f))
//
//        Row(
//            modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
//        ) {
//            Column(modifier = modifier) {
//                TextStyleTheme(
//                    modifier = modifier.padding(top = 8.dp),
//                    text = music.title,
//                    size = 16.sp,
//                    color = Color.Gray
//                )
//                TextStyleTheme(
//                    modifier = modifier.padding(top = 8.dp),
//                    text = music.description,
//                    size = 12.sp,
//                    color = Color.Gray
//                )
//            }
//
//            Spacer(modifier = Modifier.weight(1f))
//
//            val isFavoriteImage = remember { mutableStateOf(music.isFavorite) }
//
//            Image(
//                modifier = modifier
//                    .size(24.dp)
//                    .clickable(onClick = {
//                        music.isFavorite = !music.isFavorite
//                        isFavoriteImage.value = music.isFavorite
//                    }),
//                painter = fetchFavoritePainter(isFavoriteImage.value),
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//            )
//        }
//
//        val sliderPosition = remember { mutableFloatStateOf(value = 5f) }
//        val interactionSource = remember { MutableInteractionSource() }
//
//        Slider(
//            modifier = modifier
//                .fillMaxWidth()
//                .padding(top = 10.dp),
//            value = sliderPosition.floatValue,
//            onValueChange = { sliderPosition.floatValue = it },
//            valueRange = 0f..100f,
//            onValueChangeFinished = {
//
//            },
//            colors = SliderDefaults.colors(
//                thumbColor = ProgressTrackColor,
//                activeTrackColor = ProgressColor,
//                activeTickColor = ProgressColor,
//                inactiveTickColor = ProgressTrackColor,
//                inactiveTrackColor = ProgressTrackColor,
//            ),
//            thumb = {
//                SliderDefaults.Thumb( //androidx.compose.material3.SliderDefaults
//                    interactionSource = interactionSource,
//                    thumbSize = DpSize(8.dp, 4.dp),
//                    colors = SliderDefaults.colors(thumbColor = ProgressColor)
//                )
//            },
//        )
//
//        Row(
//            modifier = modifier
//                .fillMaxWidth()
//                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
//        ) {
//            MusicProgressText(text = stringResource(id = R.string.tx_default_time))
//            Spacer(modifier = modifier.weight(1f))
//            MusicProgressText(text = stringResource(id = R.string.tx_default_time))
//        }
//
//        Row(
//            modifier = modifier.padding(horizontal = 16.dp, vertical = 24.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            MusicButtons(
//                modifier = modifier,
//                boxSize = 42.dp,
//                imageSize = 18.dp,
//                imageIcon = R.drawable.group
//            )
//
//            Spacer(modifier = modifier.weight(1f))
//
//            MusicButtons(
//                modifier = modifier, boxSize = 52.dp, imageSize = 24.dp, imageIcon = R.drawable.back
//            )
//
//            Spacer(modifier = modifier.weight(1f))
//
//            val isPlaying = false
//
//            MusicButtons(
//                modifier = modifier.clickable { },
//                boxSize = 62.dp,
//                imageSize = 28.dp,
//                imageIcon = fetchIsPlayingIdPainter(isFirstImage = isPlaying)
//            )
//
//            Spacer(modifier = modifier.weight(1f))
//
//            MusicButtons(
//                modifier = modifier, boxSize = 52.dp, imageSize = 24.dp, imageIcon = R.drawable.next
//            )
//        }
//    }
//}
//
//@Composable
//private fun fetchFavoritePainter(isFirstImage: Boolean): Painter =
//    if (isFirstImage) painterResource(R.drawable.isliked)
//    else painterResource(R.drawable.not_liked)
//
//@Composable
//private fun fetchIsPlayingIdPainter(isFirstImage: Boolean): Int = if (isFirstImage) R.drawable.pause
//else R.drawable.play
//
////@Composable
////fun fletchFromPlayingText(isFromPlaylist: Boolean): String = stringResource(
////    id = if (isFromPlaylist) R.string.tx_playlist else R.string.tx_main_list
//)