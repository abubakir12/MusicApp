package com.example.musicapp.presentation.screen.detail

import androidx.navigation.NavType
import androidx.navigation.navArgument


interface Destination {

    val routeWithArgs: String
    val route: String
}

object DetailDestination : Destination {

    val musicIdKey = "musicIdKey"
    override val route: String = "detail_screen"
    override val routeWithArgs: String = "$route/{$musicIdKey}"
    val arguments = listOf(navArgument(musicIdKey) { type = NavType.StringType })

}