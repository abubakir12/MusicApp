package com.example.musicapp.presentation.splash

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private const val SPLASH_DELAY_TIME = 30_0L

@HiltViewModel
class SplashViewModel @Inject constructor(
//    private val navigator: Navigator,
//    private val shouldOnBoardingPassedUseCases: ShouldOnBoardingPassedUseCases
) : ViewModel() {

    init {
//        val isOnBoardingPassed = shouldOnBoardingPassedUseCases()
//        viewModelScope.launch {
//            delay(SPLASH_DELAY_TIME)
//            val destination = when {
//                isOnBoardingPassed -> AUTH_NAV_GRAPH_ROUTE
//                else -> AUTH_NAV_GRAPH_ROUTE
//            }
//            navigator.navigateTo(destination, true)
//        }
    }
}