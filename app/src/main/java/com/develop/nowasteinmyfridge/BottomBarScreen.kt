package com.develop.nowasteinmyfridge

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector,
) {
    data object Home : BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home,
    )

    data object Inventory : BottomBarScreen(
        route = "inventory",
        title = "Inventory",
        icon = Icons.Default.List,
    )

    data object Adding : BottomBarScreen(
        route = "adding",
        title = "Adding",
        icon = Icons.Default.Add,
    )

    data object Setting : BottomBarScreen(
        route = "setting",
        title = "Setting",
        icon = Icons.Default.AccountCircle,
    )
}