package com.develop.nowasteinmyfridge

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.develop.nowasteinmyfridge.feature.adding.Adding
import com.develop.nowasteinmyfridge.feature.inventory.Inventory
import com.develop.nowasteinmyfridge.feature.setting.Setting
import com.develop.nowasteinmyfridge.feature.home.HomeScreen
import com.develop.nowasteinmyfridge.ui.theme.BottomBarScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen()
        }
        composable(route = BottomBarScreen.Inventory.route) {
            Inventory()
        }
        composable(route = BottomBarScreen.Adding.route) {
            Adding()
        }
        composable(route = BottomBarScreen.Setting.route) {
            Setting()
        }
    }
}