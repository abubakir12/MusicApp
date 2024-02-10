package com.example.musicapp.presentation.onboarding

import androidx.lifecycle.ViewModel
import com.example.musicapp.presentation.manager.Navigator
import com.example.musicapp.presentation.navigation.nav_graph.MAIN_NAV_GRAPH_ROUTE
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {

    fun onboardingFinished() {
        navigator.navigateTo(MAIN_NAV_GRAPH_ROUTE, true)
    }
}