@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.sa.sharedelementtransition.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.ArcMode
import androidx.compose.animation.core.ExperimentalAnimationSpecApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sa.sharedelementtransition.R
import com.sa.sharedelementtransition.data.Constants
import com.sa.sharedelementtransition.data.Utils.categories
import com.sa.sharedelementtransition.data.Utils.coffeeList
import com.sa.sharedelementtransition.model.Coffee
import com.sa.sharedelementtransition.ui.theme.Active
import com.sa.sharedelementtransition.ui.theme.Background
import com.sa.sharedelementtransition.ui.theme.CharcoalGray
import com.sa.sharedelementtransition.ui.theme.CoffeeNameColor
import com.sa.sharedelementtransition.ui.theme.CoffeePriceColor
import com.sa.sharedelementtransition.ui.theme.CoffeeTypeColor
import com.sa.sharedelementtransition.ui.theme.Lighter
import com.sa.sharedelementtransition.ui.theme.SharedElementTransitionTheme
import com.sa.sharedelementtransition.ui.theme.WarmOrange
import com.sa.sharedelementtransition.ui.theme.soraFontFamily
import java.util.Locale


@Composable
fun HomeScreen(
    coffees: List<Coffee>,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    navToDetail: (Int) -> Unit,
) {

    var selectedCategory by remember { mutableStateOf(categories[0]) }
    Column(modifier = Modifier.background(Background)) {
        TopScreen()

        Categories(
            selectedCategoryItem = selectedCategory,
            onClick = { name -> selectedCategory = name }
        )

        LazyVerticalGrid(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxSize()
                .background(Background),
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(24.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(coffees) { coffee ->
                CoffeeItem(
                    coffee,
                    navToDetail,
                    sharedTransitionScope,
                    animatedContentScope
                )

            }
        }
    }
}

@OptIn(ExperimentalAnimationSpecApi::class)
@Composable
private fun CoffeeItem(
    coffee: Coffee,
    navToDetail: (Int) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
) {
    with(sharedTransitionScope) {
        val boundsTransform = BoundsTransform { initialBounds, targetBounds ->
            keyframes {
                durationMillis = 500
                initialBounds at 0 using ArcMode.ArcBelow using FastOutSlowInEasing
                targetBounds at 500
            }
        }

        val textBoundsTransform = { _: Rect, _: Rect -> tween<Rect>(500) }

        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier
                    .clickable {
                        navToDetail(coffee.id)
                    }
                    .padding(8.dp)

            ) {
                Box {
                    Image(
                        modifier = Modifier
                            .sharedElement(
                                sharedTransitionScope.rememberSharedContentState(key = "${Constants.KEY_COFFEE_IMAGE}-${coffee.id}"),
                                animatedVisibilityScope = animatedContentScope,
                                renderInOverlayDuringTransition = false,
                                boundsTransform = boundsTransform
                            )
                            .fillMaxWidth()
                            .height(128.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        painter = painterResource(id = coffee.image),
                        contentDescription = "coffee image",
                        contentScale = ContentScale.Crop,
                    )

                    Image(
                        modifier = Modifier.align(Alignment.TopEnd),
                        painter = painterResource(id = R.drawable.rating),
                        contentDescription = null
                    )

                }

                Text(
                    modifier = Modifier
                        .sharedBounds(
                            sharedTransitionScope.rememberSharedContentState(key = "${Constants.KEY_COFFEE_NAME}-${coffee.id}"),
                            animatedVisibilityScope = animatedContentScope,
                            renderInOverlayDuringTransition = false,
                            boundsTransform = textBoundsTransform
                        ),
                    text = coffee.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = CoffeeNameColor,
                    fontFamily = soraFontFamily
                )

                Text(
                    modifier = Modifier
                        .sharedBounds(
                            sharedTransitionScope.rememberSharedContentState(key = "${Constants.KEY_COFFEE_TYPE}-${coffee.id}"),
                            animatedVisibilityScope = animatedContentScope,
                            renderInOverlayDuringTransition = false,
                            boundsTransform = textBoundsTransform
                        ),
                    text = coffee.coffeeType,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = CoffeeTypeColor,
                    fontFamily = soraFontFamily
                )

                Row(
                    modifier = Modifier.padding(top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = String.format(Locale.US, "EGP %.2f", coffee.price),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = CoffeePriceColor,
                        fontFamily = soraFontFamily
                    )
                    Spacer(modifier = Modifier.weight(1f))

                    Image(
                        painter = painterResource(id = R.drawable.ic_plus),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Composable
fun Categories(
    selectedCategoryItem: String,
    onClick: (String) -> Unit,
) {


    LazyRow(
        contentPadding = PaddingValues(horizontal = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) { name ->
            CategoryItem(
                selectedCategoryItem == name,
                name,
                onClick = { onClick(it) }
            )

        }
    }
}

@Composable
fun CategoryItem(
    isSelected: Boolean,
    name: String,
    onClick: (String) -> Unit,
) {
    Text(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) WarmOrange else Color.Transparent)
            .clickable { onClick(name) }
            .padding(horizontal = 10.dp, vertical = 6.dp),
        text = name,
        color = if (isSelected) Color.White else CharcoalGray,
        fontSize = 14.sp,
        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
        fontFamily = soraFontFamily
    )
}

@Composable
fun TopScreen() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(390.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .paint(painterResource(id = R.drawable.container))
                .padding(24.dp)
        ) {

            Text(
                modifier = Modifier.padding(top = 24.dp),
                text = "Location",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Lighter,
                fontFamily = soraFontFamily
            )

            Row(
                modifier = Modifier.padding(top = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Cairo, Nasr City",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Active,
                    fontFamily = soraFontFamily
                )

                Image(
                    painter = painterResource(id = R.drawable.down_active),
                    contentDescription = "arrow down"
                )
            }

            Image(
                modifier = Modifier.padding(top = 24.dp),
                painter = painterResource(id = R.drawable.search_bar),
                contentDescription = "search text field"
            )

        }

        Image(
            modifier = Modifier
                .padding(24.dp)
                .align(Alignment.BottomCenter),
            painter = painterResource(id = R.drawable.banner),
            contentDescription = "search text field"
        )
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SharedElementTransitionTheme {
        SharedTransitionLayout {
            val state by remember { mutableStateOf(true) }

            AnimatedContent(targetState = state, label = "", content = {
                if (it)
                    HomeScreen(
                        coffeeList,
                        this@SharedTransitionLayout,
                        this
                    ) {}
            })

        }
    }
}