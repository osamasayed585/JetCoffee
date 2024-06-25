package com.droidos.jetcoffee.data

import com.droidos.jetcoffee.R
import com.droidos.jetcoffee.model.Coffee

object Utils {
// https://jsonware.com/api/v1/json/296397f2-62cf-4273-8cc0-f10853c4f2f1

    val categories = listOf(
        "All Coffee",
        "Espresso",
        "Cappuccino",
        "Latte",
        "Americano",
        "Macchiato",
        "Flat White"
    )

    val coffee = Coffee(
        name = "Caffe Mocha",
        rate = "4.8",
        countOfRate = 230,
        type = "Ice/Hot",
        size = listOf("S", "M", "L"),
        description = "A cappuccino is an approximately 150 ml (5 oz) beverage, with 25 ml of espresso coffee and 85ml of fresh milk the fo.. Read More.",
        price = 9.50,
        image = R.drawable.coffee_image2,
        id = 1,
        coffeeType = "Mocha"
    )

    val coffeeList = listOf(
        Coffee(
            name = "Caffe Mocha",
            rate = "4.8",
            countOfRate = 230,
            type = "Ice/Hot",
            size = listOf("S", "M", "L"),
            description = "A cappuccino is an approximately 150 ml (5 oz) beverage, with 25 ml of espresso coffee and 85ml of fresh milk the fo.. Read More.",
            price = 9.50,
            image = R.drawable.coffee_image2,
            id = 122,
            coffeeType = "Mocha"
        ),
        Coffee(
            name = "Flat White",
            rate = "4.7",
            countOfRate = 130,
            type = "Hot",
            size = listOf("S", "M"),
            description = "Espresso with velvety steamed milk.",
            price = 8.49,
            image = R.drawable.coffee_image3,
            id = 4,
            coffeeType = "Espresso"
        ),
        Coffee(
            name = "Cappuccino",
            rate = "4.5",
            countOfRate = 120,
            type = "Hot",
            size = listOf("S", "M", "L"),
            description = "Espresso mixed with frothed milk and steamed milk foam.",
            price = 15.99,
            image = R.drawable.coffee_image6,
            id = 134,
            coffeeType = "Espresso"
        ),
        Coffee(
            name = "Latte",
            rate = "4.8",
            countOfRate = 150,
            type = "Hot",
            size = listOf("S", "M", "L"),
            description = "Espresso with steamed milk and a small amount of milk foam.",
            price = 10.29,
            image = R.drawable.coffee_image4,
            id = 2,
            coffeeType = "Espresso"
        ),
        Coffee(
            name = "Iced Americano",
            rate = "4.3",
            countOfRate = 100,
            type = "Ice",
            size = listOf("S", "M", "L"),
            description = "Espresso with chilled water and ice cubes.",
            price = 5.49,
            image = R.drawable.coffee_image5,
            id = 3,
            coffeeType = "Americano"
        )
    )


}