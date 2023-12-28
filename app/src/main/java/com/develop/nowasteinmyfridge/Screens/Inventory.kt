package com.develop.nowasteinmyfridge.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun Inventory(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF4F5FB))
    ) {
        Text(
            text = "Inventory",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White
        )
    }
}