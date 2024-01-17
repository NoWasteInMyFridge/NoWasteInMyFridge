package com.develop.nowasteinmyfridge

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.develop.nowasteinmyfridge.feature.adding.AddingScreen
import com.develop.nowasteinmyfridge.feature.home.HomeScreen
import com.develop.nowasteinmyfridge.feature.inventory.DataViewModel
import com.develop.nowasteinmyfridge.feature.inventory.InventoryScreen
import com.develop.nowasteinmyfridge.feature.setting.SettingScreen
import com.develop.nowasteinmyfridge.ui.theme.BottomBarScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    val viewModel: DataViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen()
        }
        composable(route = BottomBarScreen.Inventory.route) {
            InventoryScreen()
        }
        composable(route = BottomBarScreen.Adding.route) {
            val dataViewModel: DataViewModel = viewModel()
            AddingScreen(dataViewModel = dataViewModel)
        }


        composable(route = BottomBarScreen.Setting.route) {
            SettingScreen()
        }
    }
}