package com.develop.nowasteinmyfridge

sealed class Screen(val route: String) {
    data object MainScreen : Screen("main_screen")
    data object LoginScreen : Screen("login_screen")
}