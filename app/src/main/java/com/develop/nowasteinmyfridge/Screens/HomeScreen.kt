package com.develop.nowasteinmyfridge.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun HomeScreen(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Green) // Set the background color to pink
    ) {
        Text(
            text = "Adding",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White // Set the text color to white for better visibility
        )
    }
}