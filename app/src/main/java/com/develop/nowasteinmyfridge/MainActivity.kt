package com.develop.nowasteinmyfridge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.develop.nowasteinmyfridge.ui.theme.NoWasteInMyFridgeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoWasteInMyFridgeTheme {
                Navigation()
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    Navigation()
}
