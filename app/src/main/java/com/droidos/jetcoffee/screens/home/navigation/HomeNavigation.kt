@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.droidos.jetcoffee.screens.home.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.droidos.jetcoffee.screens.home.HomeRoute

const val HomeRoute = "HomeScreen"


fun NavGraphBuilder.homeScreen(
    navToCoffeeDetail: (Int) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
) {
    composable(HomeRoute) {
        HomeRoute(navToCoffeeDetail,
            sharedTransitionScope,
            this@composable)
    }
}

fun NavController.navigateToHomeScreen(topLevelNavOptions: NavOptions? = null) =
    navigate(HomeRoute, topLevelNavOptions)

