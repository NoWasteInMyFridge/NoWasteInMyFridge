package com.develop.nowasteinmyfridge

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.develop.nowasteinmyfridge.feature.login.LoginScreen
import com.develop.nowasteinmyfridge.feature.signup.SignupScreen

const val AUTH_GRAPH_ROUTE = "auth"
fun NavGraphBuilder.authNavGraph(
    navHostController: NavHostController,
) {
    navigation(
        startDestination = Screen.LoginScreen.route,
        route = AUTH_GRAPH_ROUTE,
    ) {
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navHostController)
        }
        composable(route = Screen.SignUpScreen.route) {
            SignupScreen(navController = navHostController)
        }
    }
}