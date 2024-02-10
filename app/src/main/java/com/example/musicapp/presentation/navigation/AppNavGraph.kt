package com.example.musicapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.musicapp.presentation.navigation.nav_graph.MAIN_NAV_GRAPH_ROUTE
import com.example.musicapp.presentation.navigation.nav_graph.MainNavGraphRoot
import com.example.musicapp.presentation.onboarding.OnBoardingDestination
import com.example.musicapp.presentation.onboarding.OnBoardingScreen
import com.example.musicapp.presentation.onboarding.OnBoardingViewModel
import com.example.musicapp.presentation.screen.detail.DetailDestination
import com.example.musicapp.presentation.screen.detail.DetailScreen
import com.example.musicapp.presentation.screen.detail.DetailsScreenViewModel
import com.example.musicapp.presentation.splash.SplashDestination
import com.example.musicapp.presentation.splash.SplashScreen
import com.example.musicapp.presentation.splash.SplashViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = SplashDestination.route
    ) {
        composable(SplashDestination.route) {
            val viewModel: SplashViewModel = hiltViewModel()
            viewModel
            SplashScreen(onNavigateToOnBoardingScreen = {
                navController.navigate(OnBoardingDestination.route)
            })
        }
        composable(OnBoardingDestination.route) {
            val viewModel: OnBoardingViewModel = hiltViewModel()
            viewModel
            OnBoardingScreen(navigateToHomeScreen = {
                viewModel.onboardingFinished()
            })
        }
        composable(MAIN_NAV_GRAPH_ROUTE) {
            MainNavGraphRoot()
        }
    }
}