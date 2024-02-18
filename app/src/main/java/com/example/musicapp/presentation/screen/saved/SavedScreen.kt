package com.example.musicapp.presentation.screen.saved

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.musicapp.R
import com.example.musicapp.presentation.component.MusicHorizontalItem
import kotlinx.coroutines.flow.StateFlow

@Composable
fun SavedScreen(
    uiStateFlow: StateFlow<SavedScreenUiState>,
    navigateToDetails: (String) -> Unit,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val uiState = uiStateFlow.collectAsStateWithLifecycle().value

    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        item {
            WatchListScreenHeader(
                navController = navController
            )
        }
        items(
            items = uiState.savedList
        ) { music ->
            MusicHorizontalItem(
                title = music.title,
                musicId = music.objectId,
                posterUrl = music.image.toString(),
                navigateToDetails = {
                    navigateToDetails(it)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WatchListScreenHeader(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(title = {
        Text(
            modifier = modifier,
            text = stringResource(id = R.string.saved),
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
