package com.develop.nowasteinmyfridge

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.develop.nowasteinmyfridge.feature.adding.AddingScreen
import com.develop.nowasteinmyfridge.feature.home.HomeScreen
import com.develop.nowasteinmyfridge.feature.inventory.InventoryScreen
import com.develop.nowasteinmyfridge.feature.setting.SettingScreen
import com.develop.nowasteinmyfridge.feature.setting.navigation.settingNavGraph

const val MAIN_GRAPH_ROUTE = "main"
@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = MAIN_GRAPH_ROUTE,
        startDestination = BottomBarScreen.Home.route,
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen()
        }
        composable(route = BottomBarScreen.Inventory.route) {
            InventoryScreen()
        }
        composable(route = BottomBarScreen.Adding.route) {
            AddingScreen(navController)
        }
        settingNavGraph(navController)
    }
}
