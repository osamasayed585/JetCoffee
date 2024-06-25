package com.droidos.jetcoffee.screens.home.events

import com.droidos.jetcoffee.model.Coffee
import com.droidos.jetcoffee.screens.base.ScreenEvent


sealed class HomeEvents : ScreenEvent {

    data class OnCoffeeLoaded(
        val coffees: List<Coffee>,
        val categories: List<String>
    ) : HomeEvents()

    data class OnSelectCategory(val categoryId: Int) : HomeEvents()
}