package com.example.musicapp.presentation.app

import androidx.lifecycle.ViewModel
import com.example.musicapp.presentation.manager.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {
    val destinationFlow = navigator.destinationFlow()
}