@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.droidos.jetcoffee.screens.detail.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.droidos.jetcoffee.data.Constants.Key_Id
import com.droidos.jetcoffee.screens.detail.CoffeeDetailRoute
import com.droidos.jetcoffee.screens.detail.CoffeeDetailsScreen
import com.droidos.jetcoffee.screens.home.HomeRoute

const val DetailRoute = "DetailScreen/{id}"


fun NavGraphBuilder.detailScreen(
    sharedTransitionScope: SharedTransitionScope,
) {
    composable(
        DetailRoute,
        arguments = listOf(navArgument(Key_Id) { type = NavType.IntType })
    ) {
        CoffeeDetailRoute(
            sharedTransitionScope = sharedTransitionScope,
            this@composable,
        )
    }
}

fun NavController.navigateToDetailScreen(id: Int) = navigate("DetailScreen/$id")
