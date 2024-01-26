package com.develop.nowasteinmyfridge

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.develop.nowasteinmyfridge.feature.login.LoginScreen

@Preview
@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AUTH_GRAPH_ROUTE) {
        authNavGraph(navController)
        composable(route = MAIN_GRAPH_ROUTE) {
            MainScreen()
        }
    }
}
