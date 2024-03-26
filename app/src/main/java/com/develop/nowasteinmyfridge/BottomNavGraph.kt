package com.develop.nowasteinmyfridge


import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.develop.nowasteinmyfridge.feature.adding.AddingScreen
import com.develop.nowasteinmyfridge.feature.home.HomeScreen
import com.develop.nowasteinmyfridge.feature.home.MenuScreen
import com.develop.nowasteinmyfridge.feature.inventory.InventoryScreen
import com.develop.nowasteinmyfridge.feature.setting.navigation.settingNavGraph

const val MAIN_GRAPH_ROUTE = "main"
const val MENU_SCREEN_ROUTE = "menu/{name}/{image}/{ingredients}"
@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = MAIN_GRAPH_ROUTE,
        startDestination = BottomBarScreen.Home.route,
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = BottomBarScreen.Inventory.route) {
            InventoryScreen()
        }
        composable(route = BottomBarScreen.Adding.route) {
            AddingScreen(navController)
        }
        settingNavGraph(navController)
        composable(
            route = MENU_SCREEN_ROUTE,
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val name = arguments.getString("name") ?: ""
            val image = arguments.getString("image") ?: ""
            val ingredients = arguments.getString("ingredients") ?: ""

            Log.d("MenuScreen", "Pop $ingredients")
            MenuScreen(
                name = name,
                image = image,
                ingredients = ingredients
            )
        }
    }
}
