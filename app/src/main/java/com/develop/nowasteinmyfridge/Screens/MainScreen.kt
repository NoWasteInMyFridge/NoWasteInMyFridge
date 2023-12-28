package com.develop.nowasteinmyfridge.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.develop.nowasteinmyfridge.BottomNavGraph
import com.develop.nowasteinmyfridge.ui.theme.BottomBarScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController){
    val navController = rememberNavController()
    Scaffold (
        bottomBar = { BottomBar(navController = navController)}
        ) {
        BottomNavGraph(navController = navController)
    }
}
@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Inventory,
        BottomBarScreen.Adding,
        BottomBarScreen.Setting,
    )
    val currentDestination = navController.currentBackStackEntry?.destination

    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color(0xFF888888),
        modifier = Modifier.height(64.dp)
    ){
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
//        label = {
//            Text(text = screen.title)
//        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon",
                tint = if (currentDestination?.route == screen.route) Color(0xFF888888) else Color.Unspecified,
                modifier = Modifier.size(36.dp)
            )
        },
        selected = currentDestination?.route == screen.route,
        onClick = {
            if (currentDestination?.route != screen.route) {
                navController.navigate(screen.route)
            }
        }
    )
}
