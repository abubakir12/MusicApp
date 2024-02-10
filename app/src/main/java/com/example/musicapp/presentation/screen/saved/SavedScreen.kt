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
import androidx.compose.material.icons.filled.KeyboardArrowLeft
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
    navigateToDetails: (Int) -> Unit,
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
                navigateToDetails = navigateToDetails
            )
        }
    }
}

@Composable
fun WatchListScreenHeader(
    navController: NavHostController,
    modifier: Modifier = Modifier,
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
            )
            Spacer(modifier = modifier.weight(1f))
            Text(
                text = stringResource(id = R.string.saved),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.SemiBold, color = if (isSystemInDarkTheme()) Color.White
                    else Color.Black
                )
            )
            Spacer(modifier = modifier.weight(1f))
        }
    }
}
