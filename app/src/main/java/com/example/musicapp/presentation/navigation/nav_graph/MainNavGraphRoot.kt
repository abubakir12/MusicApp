package com.example.musicapp.presentation.navigation.nav_graph

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.musicapp.presentation.navigation.AppBottomNavigation
import com.example.musicapp.presentation.navigation.BottomTap
import com.example.musicapp.presentation.screen.detail.DetailDestination
import com.example.musicapp.presentation.screen.detail.DetailScreen
import com.example.musicapp.presentation.screen.home.HomeScreen
import com.example.musicapp.presentation.screen.home.HomeViewModel
import com.example.musicapp.presentation.screen.saved.SavedScreen
import com.example.musicapp.presentation.screen.saved.SavedScreenViewModel
import com.example.musicapp.presentation.screen.search.SearchScreen
import com.example.musicapp.presentation.screen.search.SearchViewModel

const val MAIN_NAV_GRAPH_ROUTE = "main_nav_graph_route"

@Composable
fun MainNavGraphRoot() {
    val navHostController = rememberNavController()

    Scaffold(bottomBar = {
        AppBottomNavigation(navController = navHostController)
    }) { innerPaddings ->
        NavHost(
            modifier = Modifier.padding(bottom = innerPaddings.calculateBottomPadding()),
            navController = navHostController,
            startDestination = BottomTap.HOME.route,
        ) {
            composable(BottomTap.HOME.route) {
                val viewModel: HomeViewModel = hiltViewModel()
//                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                HomeScreen(
                    musics = viewModel.musics.collectAsStateWithLifecycle().value,
                    navigateToDetails = { musicId ->
                        navHostController.navigate("${DetailDestination.route}/$musicId")
                    },
                    navigateToSearchScreenCallback = {
                        navHostController.navigate(
                            BottomTap.SEARCH.route
                        )
                    },
                )
            }
            composable(
                route = DetailDestination.routeWithArgs, arguments = DetailDestination.arguments
            ) { navBackStackEntry ->
                val musicId =
                    navBackStackEntry.arguments?.getString(DetailDestination.musicIdKey) ?: String()
                val viewModel: HomeViewModel = hiltViewModel()
                DetailScreen(
                    music = viewModel.playingMusic.collectAsState().value,
                    isSaved = viewModel.isSaved.collectAsState().value,
                    fetchMusic = { },
                    navController = navHostController,
                )
            }
            composable(BottomTap.SEARCH.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val uiState by viewModel.uiStateFlow.collectAsState()
                SearchScreen(
                    onValueChange = viewModel::onValueChange, navigateToDetails = { musicId ->
                        navHostController.navigate("${DetailDestination.route}/$musicId")
                    }, navController = navHostController, uiState = uiState
                )
            }
            composable(BottomTap.SAVED.route) {
                val viewModel: SavedScreenViewModel = hiltViewModel()
                viewModel.fetchAllSavedMusic()
                SavedScreen(uiStateFlow = viewModel.uiStateFlow,
                    navController = navHostController,
                    navigateToDetails = { musicId ->
                        navHostController.navigate("${DetailDestination.route}/$musicId")
                    })
            }
        }
    }
}
