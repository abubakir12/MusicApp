package com.example.musicapp.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.musicapp.R
import com.example.musicapp.presentation.theme.GrayMusic
import com.example.musicapp.presentation.theme.TabBar

enum class BottomTap(
    @DrawableRes val iconId: Int,
    val title: String,
    val route: String
) {
    HOME(
        iconId = R.drawable.home_icon,
        title = "Home",
        route = "home_screen"
    ),
    SEARCH(
        iconId = R.drawable.search_icon,
        title = "Search",
        route = "search_screen"
    ),
    SAVED(
        iconId = R.drawable.saved_icon,
        title = "Saved",
        route = "saved_screen"
    )
}

@Composable
fun AppBottomNavigation(
    navController: NavHostController
) {
    val tabs = BottomTap.entries.toList()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottomNavigation (
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = MaterialTheme.colorScheme.surface
    ) {
        tabs.forEach { bottomTap ->
            AppBottomNavigationItem(
                modifier = Modifier.weight(1f),
                selected = currentRoute == bottomTap.route,
                onClick = {
                    navController.navigate(bottomTap.route) { launchSingleTop = true }
                },
                icon = painterResource(id = bottomTap.iconId)
            )
        }
    }
}

private const val DEFAULT_ICON_SIZE = 50


@Composable
fun AppBottomNavigationItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: Painter,
    modifier: Modifier = Modifier,
    iconSize: Dp = DEFAULT_ICON_SIZE.dp,
) {
    val scale = if (selected) 1.5f else 1.0f

    val color = if (selected) TabBar
    else MaterialTheme.colorScheme.secondary

    val animatedScale: Float by animateFloatAsState(
        targetValue = scale,
        animationSpec = TweenSpec(
            durationMillis = 500,
            easing = FastOutSlowInEasing
        ), label = ""
    )
    val animatedColor by animateColorAsState(
        targetValue = color,
        animationSpec = TweenSpec(
            durationMillis = 500,
            easing = FastOutSlowInEasing
        ), label = ""
    )

    IconButton(
        onClick = onClick,
        modifier = modifier.size(iconSize)
    ) {
        Icon(
            painter = icon,
            contentDescription = String(),
            tint = animatedColor,
            modifier = Modifier.scale(animatedScale)
        )
    }
}