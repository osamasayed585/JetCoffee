@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.droidos.jetcoffee.jetCoffee

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.droidos.jetcoffee.screens.detail.navigation.detailScreen
import com.droidos.jetcoffee.screens.detail.navigation.navigateToDetailScreen
import com.droidos.jetcoffee.screens.home.navigation.HomeRoute
import com.droidos.jetcoffee.screens.home.navigation.homeScreen

@Preview
@Composable
fun JetCoffeeNavHost() {
    SharedTransitionLayout {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = HomeRoute
        ) {
            homeScreen(
                navToCoffeeDetail = navController::navigateToDetailScreen,
                sharedTransitionScope = this@SharedTransitionLayout,
            )

            detailScreen(sharedTransitionScope = this@SharedTransitionLayout)
        }

    }
}