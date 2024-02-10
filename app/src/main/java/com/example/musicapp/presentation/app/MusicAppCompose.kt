package com.example.musicapp.presentation.app

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.rememberNavController
import com.example.musicapp.presentation.navigation.AppNavGraph
import kotlinx.coroutines.flow.Flow

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MusicAppCompose(
    destinationFlow: Flow<Pair<String, Boolean>>,
    modifier: Modifier = Modifier
) {
    val navHostController = rememberNavController()

    val (destination, isClearBackStack) = destinationFlow.collectAsState(initial = "" to false).value

    if (destination.isNotEmpty()) {
        navHostController.navigate(destination) {
            if (isClearBackStack) popUpTo(0)
        }
    }
    Scaffold(
        modifier = modifier.fillMaxSize()
    ) {
        AppNavGraph(
            navController = navHostController
        )
    }
}