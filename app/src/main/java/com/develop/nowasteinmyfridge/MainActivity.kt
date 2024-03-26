package com.develop.nowasteinmyfridge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.develop.nowasteinmyfridge.domain.CheckStillLoggedInUserCase
import com.develop.nowasteinmyfridge.ui.theme.NoWasteInMyFridgeTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var checkStillLoggedInUserCase: CheckStillLoggedInUserCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val startDestination: String = if (checkStillLoggedInUserCase.invoke()) {
            MAIN_GRAPH_ROUTE
        } else {
            AUTH_GRAPH_ROUTE
        }
        setContent {
            NoWasteInMyFridgeTheme {
                AppNavHost(startDestination = startDestination)
            }
        }
    }
}
