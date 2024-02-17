package com.example.musicapp.presentation.all.jah

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.musicapp.R
import com.example.musicapp.presentation.component.LoadingScreenForAllShow
import com.example.musicapp.presentation.component.ShowAllItem
import com.example.musicapp.presentation.component.TabBar
import com.example.musicapp.presentation.screen.detail.ItemDetailType
import com.example.musicapp.presentation.theme.ExtraDoubleSpacing
import com.example.musicapp.presentation.theme.ExtraMediumSpacing

@Composable
fun JahScreen(
    uiState: JahScreenUiState,
    navigateToDetails: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TabBar(
                headlineEnd = stringResource(id = R.string.jah_halib),
                startIcon = Icons.Default.ArrowBack,
            )
        },
    ) { innerPaddings ->
        when (uiState) {
            is JahScreenUiState.Loading -> LoadingScreenForAllShow(
                modifier = Modifier.padding(top = ExtraDoubleSpacing + ExtraMediumSpacing)
            )

            is JahScreenUiState.Loaded -> LoadedAllFaunaScreen(
                modifier = modifier
                    .padding(innerPaddings)
                    .fillMaxSize(),
                uiState = uiState,
                navigateToDetails = navigateToDetails
            )

            else -> {}
        }
    }
}


@Composable
fun LoadedAllFaunaScreen(
    uiState: JahScreenUiState.Loaded,
    navigateToDetails: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 4.dp,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        content = {
            items(
                items = uiState.jah,
                key = { it.objectId }
            ) { jah ->
                ShowAllItem(
                    backgroundImage = jah.avatar,
                    id = jah.objectId,
                    name = jah.title,
                    navigateToDetails = {
                        navigateToDetails(ItemDetailType.JAH.toString())
                    }
                )
            }
        },
        modifier = modifier
    )
}
