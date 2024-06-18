package com.sa.sharedelementtransition

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sa.sharedelementtransition.screens.MyApp
import com.sa.sharedelementtransition.ui.theme.SharedElementTransitionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SharedElementTransitionTheme {
                MyApp()
            }
        }
    }
}
