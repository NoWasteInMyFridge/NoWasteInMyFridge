package com.develop.nowasteinmyfridge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.develop.nowasteinmyfridge.ui.theme.NoWasteInMyFridgeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoWasteInMyFridgeTheme {
                AppNavHost()
            }
        }
    }
}

