@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalSharedTransitionApi::class, ExperimentalAnimationSpecApi::class
)

package com.droidos.jetcoffee.screens.detail

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.ArcMode
import androidx.compose.animation.core.ExperimentalAnimationSpecApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.droidos.jetcoffee.R
import com.droidos.jetcoffee.data.Constants
import com.droidos.jetcoffee.data.Utils.coffee
import com.droidos.jetcoffee.model.Coffee
import com.droidos.jetcoffee.ui.theme.Background
import com.droidos.jetcoffee.ui.theme.Black2A
import com.droidos.jetcoffee.ui.theme.CoffeeNameColor
import com.droidos.jetcoffee.ui.theme.CoffeeTypeColor
import com.droidos.jetcoffee.ui.theme.CreamyCoffee2
import com.droidos.jetcoffee.ui.theme.Light90
import com.droidos.jetcoffee.ui.theme.LightGray
import com.droidos.jetcoffee.ui.theme.WarmOrange
import com.droidos.jetcoffee.ui.theme.soraFontFamily


@Composable
fun CoffeeDetailRoute(
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
) {

}

@Composable
fun CoffeeDetailsScreen(
    coffee: Coffee,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    backPressed: () -> Unit,
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



        Scaffold(
            containerColor = Background,
            topBar = {
                CenterAlignedTopAppBar(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    title = {
                        Text(
                            text = "Detail",
                            color = CoffeeNameColor,
                        )
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Background
                    ),
                    navigationIcon = {
                        Icon(
                            modifier = Modifier.clickable { backPressed() },
                            painter = painterResource(id = R.drawable.left),
                            contentDescription = "back",
                            tint = Color.Unspecified
                        )
                    },
                    actions = {
                        Icon(
                            painter = painterResource(id = R.drawable.heart),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Image(
                        modifier = Modifier.Companion
                            .sharedElement(
                                sharedTransitionScope.rememberSharedContentState(key = "${Constants.KEY_COFFEE_IMAGE}-${coffee.id}"),
                                animatedVisibilityScope = animatedContentScope,
                                renderInOverlayDuringTransition = false,
                                boundsTransform = boundsTransform
                            )
                            .aspectRatio(1.6f)
                            .clip(RoundedCornerShape(8.dp)),
                        painter = painterResource(id = coffee.image),
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Column {
                            Text(
                                modifier = Modifier
                                    .sharedBounds(
                                        sharedTransitionScope.rememberSharedContentState(key = "${Constants.KEY_COFFEE_NAME}-${coffee.id}"),
                                        animatedVisibilityScope = animatedContentScope,
                                        renderInOverlayDuringTransition = false,
                                        boundsTransform = textBoundsTransform
                                    )
                                    .padding(top = 16.dp),
                                text = coffee.name,
                                color = CoffeeNameColor,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = soraFontFamily
                            )

                            Text(
                                modifier = Modifier
                                    .sharedBounds(
                                        sharedTransitionScope.rememberSharedContentState(key = "${Constants.KEY_COFFEE_TYPE}-${coffee.id}"),
                                        animatedVisibilityScope = animatedContentScope,
                                        renderInOverlayDuringTransition = false,
                                        boundsTransform = textBoundsTransform
                                    )
                                    .padding(top = 6.dp),
                                text = coffee.coffeeType,
                                color = CoffeeTypeColor,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Normal,
                                fontFamily = soraFontFamily
                            )

                            Row(
                                modifier = Modifier
                                    .padding(top = 16.dp),
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Image(
                                    painter = painterResource(id = R.drawable.start_yollow),
                                    contentDescription = null
                                )

                                Text(
                                    text = "4.8",
                                    color = Black2A,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    fontFamily = soraFontFamily
                                )

                                Text(
                                    text = "(230)",
                                    color = CoffeeTypeColor,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Normal,
                                    fontFamily = soraFontFamily
                                )
                            }
                        }

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.bike),
                                contentDescription = null
                            )


                            Image(
                                painter = painterResource(id = R.drawable.bean),
                                contentDescription = null
                            )


                            Image(
                                painter = painterResource(id = R.drawable.milk),
                                contentDescription = null
                            )
                        }
                    }

                    HorizontalDivider(modifier = Modifier.padding(16.dp))

                    Text(
                        text = "Description",
                        color = CoffeeNameColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = soraFontFamily
                    )

                    DescriptionBody()

                    Text(
                        modifier = Modifier.padding(top = 16.dp),
                        text = "Size",
                        color = CoffeeNameColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = soraFontFamily
                    )
                    Sizes()
                }

                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier
                        .background(
                            Color.White,
                            RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                        )
                        .padding(6.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Price",
                            color = Light90,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            fontFamily = soraFontFamily
                        )

                        Text(
                            modifier = Modifier.padding(top = 4.dp),
                            text = "EGP ${coffee.price}",
                            color = WarmOrange,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = soraFontFamily
                        )
                    }
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 24.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = WarmOrange),
                        onClick = { }
                    ) {
                        Text(
                            modifier = Modifier.padding(10.dp),
                            text = "Buy Now",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = soraFontFamily
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun Sizes() {
    var selector by remember { mutableIntStateOf(1) }

    Row {

        listOf("S", "M", "L").forEachIndexed { index, name ->

            Box(
                modifier = Modifier
                    .padding(end = 8.dp, top = 16.dp)
                    .border(
                        1.dp,
                        if (selector != index) LightGray else WarmOrange,
                        RoundedCornerShape(12.dp)
                    )
                    .clickable { selector = index }
                    .background(
                        if (selector != index) Color.White else CreamyCoffee2,
                        RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 48.dp, vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = name,
                    color = if (selector != index) Black2A else WarmOrange,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = soraFontFamily
                )
            }
        }
    }
}

@Composable
fun DescriptionBody() {
    var expanded by remember { mutableStateOf(true) }
    val maxLines by animateIntAsState(targetValue = if (expanded) 3 else 7, label = "lines state")
    val truncatedText =
        "A cappuccino is an approximately 150 ml (5 oz) beverage, with 25 ml of espresso coffee and 85ml of fresh milk the fo.. "
    val fullText =
        "A cappuccino is an approximately 150 ml (5 oz) beverage, with 25 ml of espresso coffee and 85ml of fresh milk the foam on top serves as an insulating layer to keep the drink warmer for longer..."
    val readMoreText = "Read More"
    val readLessText = "Read Less"

    val annotatedText = buildAnnotatedString {
        append(if (!expanded) fullText else truncatedText)
        pushStringAnnotation(tag = "READ_MORE", annotation = "read_more")
        withStyle(
            style = SpanStyle(
                color = WarmOrange,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = soraFontFamily
            )
        ) {
            append(if (expanded) readMoreText else readLessText)
        }
        pop()

    }

    ClickableText(
        text = annotatedText,
        onClick = { offset ->
            annotatedText.getStringAnnotations(tag = "READ_MORE", start = offset, end = offset)
                .firstOrNull()?.let {
                    expanded = !expanded
                }
        },
        maxLines = maxLines,
        style = MaterialTheme.typography.bodyLarge.copy(
            color = CoffeeTypeColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = soraFontFamily
        )
    )

}

@Preview
@Composable
fun CoffeeDetailsPreview() {
    SharedTransitionLayout {
        val state by remember { mutableStateOf(true) }

        AnimatedContent(targetState = state, label = "", content = {
            if (it)
                CoffeeDetailsScreen(
                    coffee = coffee,
                    this@SharedTransitionLayout,
                    this@AnimatedContent,
                    backPressed = {}
                )
        })

    }
}
