package com.develop.nowasteinmyfridge

sealed class Screen(val route: String) {
    data object LoginScreen : Screen("login_screen")
    data object SignUpScreen : Screen("SignUpScreen")
}