package com.droidos.jetcoffee.screens.home

import com.droidos.jetcoffee.data.Utils.categories
import com.droidos.jetcoffee.data.Utils.coffeeList
import com.droidos.jetcoffee.screens.base.BaseViewModel
import com.droidos.jetcoffee.screens.home.events.HomeEvents
import com.droidos.jetcoffee.screens.home.state.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
) : BaseViewModel<HomeUiState, HomeEvents>(HomeUiState()) {

    override fun reduce(oldState: HomeUiState, sideEffect: HomeEvents) {
        when (sideEffect) {
            is HomeEvents.OnCoffeeLoaded ->
                createNewState(
                    oldState.copy(
                        coffees = sideEffect.coffees,
                        categories = sideEffect.categories
                    )
                )

            is HomeEvents.OnSelectCategory ->
                createNewState(oldState.copy(selectedCategory = sideEffect.categoryId))

        }
    }

    init {
        emitEvent(HomeEvents.OnCoffeeLoaded(coffeeList, categories))
    }
}