package com.develop.nowasteinmyfridge.feature.setting.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.develop.nowasteinmyfridge.BottomBarScreen
import com.develop.nowasteinmyfridge.feature.account.AccountScreen
import com.develop.nowasteinmyfridge.feature.setting.MenuSettingScreen
import com.develop.nowasteinmyfridge.feature.setting.SettingScreen
const val SETTING_GRAPH_ROUTE = "setting_graph"

fun NavGraphBuilder.settingNavGraph(
    navHostController: NavHostController,
) {
    navigation(
        route = SETTING_GRAPH_ROUTE,
        startDestination = BottomBarScreen.Setting.route,
    ) {
        composable(route = BottomBarScreen.Setting.route) {
            SettingScreen(navHostController)
        }
        composable(route = MenuSettingScreen.Account.route){
            AccountScreen()
        }
    }

}
