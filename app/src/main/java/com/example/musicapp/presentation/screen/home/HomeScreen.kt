package com.example.musicapp.presentation.screen.home


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.musicapp.R
import com.example.musicapp.domain.models.DailyDomain
import com.example.musicapp.presentation.component.MusicComponents
import com.example.musicapp.presentation.component.Recently
import com.example.musicapp.presentation.screen.search.Music


@Composable
fun HomeScreen(
//    uiState: HomeUiState,
    musics: List<DailyDomain>,
//    music: DailyDomain,
//    progress: Float,
//    isPlaying: Boolean,
    modifier: Modifier = Modifier,
    navigateToDetails: (String) -> Unit,
    navigateToSearchScreenCallback: () -> Unit,
) {
    val fullScreenModifier = Modifier.fillMaxSize()

    Scaffold { innerPaddings ->
        LoadedHomeScreen(
            musics = musics,
            modifier = modifier.padding(innerPaddings),
            navigateToDetails = navigateToDetails,
            navController = rememberNavController(),
            navigateToSearchScreenCallback = navigateToSearchScreenCallback,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadedHomeScreen(
    navController: NavHostController,
    musics: List<DailyDomain>,
    modifier: Modifier = Modifier,
    navigateToDetails: (String) -> Unit,
    navigateToSearchScreenCallback: () -> Unit,
) {
    val (value, onValueChange) = remember { mutableStateOf("") }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.TopStart,
    ) {
        Column {
            Spacer(modifier = modifier.height(32.dp))
            Text(
                modifier = Modifier.padding(start = 31.dp),
                text = stringResource(id = R.string.danny),
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(modifier = modifier.height(24.dp))
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                textStyle = TextStyle(fontSize = 17.sp),
                shape = CircleShape,
                enabled = false,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                        tint = Color.Gray
                    )
                },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .background(Color(0xFFE7F1F1), RoundedCornerShape(30.dp))
                    .clickable {
                        navigateToSearchScreenCallback()
                    },
                placeholder = { Text(text = "Start Search") },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.DarkGray,
                )
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                modifier = Modifier.padding(start = 31.dp),
                text = stringResource(id = R.string.daily_mix),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(12.dp))
            MusicComponent(
                dailyDomain = musics,
                navigateToDetails = navigateToDetails,
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                modifier = Modifier.padding(start = 31.dp),
                text = stringResource(id = R.string.popular),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(12.dp))
            MusicLazyColumn(
                navigateToDetails = navigateToDetails,
                dailyDomain = musics
            )
        }
    }
}


@Composable
fun LoadingMainScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun MusicComponent(
    navigateToDetails: (String) -> Unit,
    dailyDomain: List<DailyDomain>
) {
    LazyRow(
        modifier = Modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 12.dp)
    ) {
        items(items = dailyDomain) { music ->
            MusicComponents(
                navigateToDetails = navigateToDetails,
                music = music,
                modifier = Modifier
            )
        }
    }
}


@Composable
fun MusicLazyColumn(
    navigateToDetails: (String) -> Unit,
    dailyDomain: List<DailyDomain>
) {
    LazyColumn(
        modifier = Modifier,
        contentPadding = PaddingValues(vertical = 12.dp),
    ) {
        items(items = dailyDomain) { music ->
            Recently(
                navigateToDetails = navigateToDetails,
                music = music,
                modifier = Modifier
            )
        }
    }
}
