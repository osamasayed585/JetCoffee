package com.droidos.jetcoffee.screens.cart.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.droidos.jetcoffee.screens.cart.CartRoute

const val CartRoute = "CartScreen"

fun NavGraphBuilder.cartRoute() {
    composable(CartRoute) {
        CartRoute()
    }
}

fun NavController.navigateToCartScreen(topLevelNavOptions: NavOptions? = null) = navigate(CartRoute)

