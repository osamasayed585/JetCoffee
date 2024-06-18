@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.sa.sharedelementtransition.screens

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sa.sharedelementtransition.data.Utils.coffeeList
import com.sa.sharedelementtransition.model.Coffee

@Preview
@Composable
fun MyApp() {
    SharedTransitionLayout {
        val navController = rememberNavController()
        val coffees by remember { mutableStateOf(coffeeList) }

        NavHost(
            navController = navController,
            startDestination = "home"
        ) {
            composable("home") {
                HomeScreen(
                    coffees,
                    this@SharedTransitionLayout,
                    this@composable
                ) { id -> navController.navigate("detail/$id") }
            }

            composable(
                "Detail/{id}",
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("id")
                val coffee = coffees.find { coffee -> coffee.id == id }

                CoffeeDetailsScreen(
                    coffee!!,
                    this@SharedTransitionLayout,
                    this@composable,
                    backPressed = navController::popBackStack
                )

            }
        }

    }
}