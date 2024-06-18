package com.sa.sharedelementtransition.model

import androidx.annotation.DrawableRes

data class Coffee(
    val name: String,
    val rate: String,
    val countOfRate: Int,
    val coffeeType: String,
    val type: String,
    val size: List<String>,
    val description: String,
    val price: Double,
    @DrawableRes val image: Int,
    val id: Int,
)
