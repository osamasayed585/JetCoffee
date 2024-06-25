package com.droidos.jetcoffee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.droidos.jetcoffee.jetCoffee.JetCoffeeApp
import com.droidos.jetcoffee.ui.theme.JetCoffeeTheme
import com.droidos.jetcoffee.units.networkMonitor.NetworkMonitor
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var networkMonitor: NetworkMonitor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetCoffeeTheme {
                val navController = rememberNavController()
                JetCoffeeApp(
                    networkMonitor = networkMonitor,
                    navController = navController
                )
            }
        }
    }
}
