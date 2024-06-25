package com.droidos.jetcoffee.screens.home.state

import com.droidos.jetcoffee.model.Coffee
import com.droidos.jetcoffee.screens.base.ScreenState

data class HomeUiState(
    val coffees: List<Coffee> = emptyList(),
    val categories: List<String> = emptyList(),
    val selectedCategory: Int = 1,
    val isLoading: Boolean = false,
) : ScreenState
