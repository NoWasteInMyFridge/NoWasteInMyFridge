package com.develop.nowasteinmyfridge.ui.theme

import android.media.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.develop.nowasteinmyfridge.R

sealed class BottomBarScreen (
    val route: String,
    val title: String,
    val icon: ImageVector
    ){
    object Home: BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )
    object Inventory: BottomBarScreen(
        route = "inventory",
        title = "Inventory",
        icon = Icons.Default.List
    )
    object Adding: BottomBarScreen(
        route = "adding",
        title = "Adding",
        icon = Icons.Default.Add
    )
    object Setting: BottomBarScreen(
        route = "setting",
        title = "Setting",
        icon = Icons.Default.AccountCircle
    )
}