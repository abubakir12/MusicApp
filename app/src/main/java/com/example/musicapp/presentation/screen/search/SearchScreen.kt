package com.example.musicapp.presentation.screen.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.musicapp.R
import com.example.musicapp.presentation.component.MusicHorizontalItem
import com.example.musicapp.presentation.theme.Background

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    uiState: SearchUiState,
    onValueChange: (String) -> Unit,
    navigateToDetails: (Int) -> Unit,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val viewModel = viewModel<SearchViewModel>()
    val searchText by viewModel.searchText.collectAsState()
    val music by viewModel.music.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()

    CenterAlignedTopAppBar(title = {
        Text(
            modifier = modifier,
            text = stringResource(id = R.string.search),
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
    Spacer(modifier = Modifier.padding(top = 48.dp))
    Column(
        modifier = modifier.padding(top = 16.dp)
    ) {
        Spacer(modifier = modifier.height(30.dp))
        TextField(
            modifier = Modifier
                .padding(horizontal = 26.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(30)),
            value = searchText,
            onValueChange = viewModel::onSearchTextChange,
            placeholder = { Text(text = "Search") }
        )

        when {
            uiState.music.isEmpty() -> NoResultsStub()
            uiState.isLoading -> LoadingScreen()
            else -> {
                LazyColumn {
                    items(
                        items = uiState.music
                    ) { music ->
                        MusicHorizontalItem(
                            title = music.title,
                            musicId = music.id,
                            posterUrl = music.avatar,
                            navigateToDetails = navigateToDetails
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun NoResultsStub(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = modifier
                .align(alignment = Alignment.Center)
        ) {
            Image(
                modifier = modifier
                    .size(32.dp),
                painter = painterResource(id = R.drawable.no_music),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.sorry_music),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Normal, color = if (isSystemInDarkTheme()) Color.White
                    else Color.Black
                ),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = R.string.find_your),
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Normal, color = if (isSystemInDarkTheme()) Color.White
                    else Color.Black
                )
            )
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}