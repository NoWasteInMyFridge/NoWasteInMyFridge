package com.develop.nowasteinmyfridge.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.develop.nowasteinmyfridge.ui.theme.BaseColor
import com.develop.nowasteinmyfridge.ui.theme.White

@Composable
fun Adding(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BaseColor)
    ) {
        Text(
            text = "",
            style = MaterialTheme.typography.bodyLarge,
            color = White
        )
    }
}