package com.example.musicapp.presentation.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.musicapp.presentation.navigation.AppNavGraph
import com.example.musicapp.presentation.theme.MusicAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicAppTheme {
                MusicAppCompose(destinationFlow = viewModel.destinationFlow)
            }
        }
    }


}
