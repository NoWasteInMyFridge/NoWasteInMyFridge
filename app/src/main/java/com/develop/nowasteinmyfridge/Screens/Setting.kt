package com.develop.nowasteinmyfridge.Screens
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun Setting() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Magenta) // Set the background color to pink
    ) {
        Text(
            text = "Setting",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White // Set the text color to white for better visibility
        )
    }
}
