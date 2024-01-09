package com.develop.nowasteinmyfridge.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.develop.nowasteinmyfridge.R
import com.develop.nowasteinmyfridge.ui.theme.BaseColor
import com.develop.nowasteinmyfridge.ui.theme.Black
import com.develop.nowasteinmyfridge.ui.theme.GrayPrimary
import com.develop.nowasteinmyfridge.ui.theme.White


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 48.dp)
            .background(color = BaseColor)
    ) {
        Scaffold {
            Column(

                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween

            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(fraction = 0.2f)
                ) {
                    Image(
                        painter = painterResource(id = R.mipmap.hometopper),
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )
                    Column(
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.CenterStart)
                            .padding(start = 20.dp, top = 16.dp)
                    ) {
                        Text(
                            text = buildAnnotatedString {
                                for (letter in "Hi, Ampere") {
                                    withStyle(
                                        style = SpanStyle(
                                            shadow = Shadow(
                                                color = Black,
                                                offset = Offset(2f, 2f),
                                                blurRadius = 4f
                                            )
                                        )
                                    ) {
                                        append(letter.toString())
                                    }
                                }
                            },
                            style = MaterialTheme.typography.headlineMedium,
                            color = White
                        )
                    }
                    Column(
                        modifier = Modifier
                            .wrapContentSize()
                            .align(Alignment.BottomStart)
                            .padding(start = 20.dp, bottom = 10.dp)
                    ) {
                        Text(
                            text = "Ingredients",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Black
                        )

                    }

                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = BaseColor)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        SliderBoxComponent(names = listOf("Apple", "Pork", "Peach", "pie"))
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Today Option",
                            style = MaterialTheme.typography.titleMedium,
                            color = Black
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        SliderBoxComponentVertical(names = listOf("Salad", "Apple Pie", "Cocopop", "Muchupichu", "Apple Pie", "Cocopop", "Muchupichu"))
                    }
                }
            }

        }
    }
}

@Composable
fun SliderBoxComponent(names: List<String>, modifier: Modifier = Modifier) {
    var selectedIndex by remember { mutableIntStateOf(0) }

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
    ) {
        itemsIndexed(names) { index, name ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 14.dp, vertical = 8.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.background)
                    .clickable {
                        selectedIndex = index
                    }
                    .border(
                        width = 2.dp,
                        color = GrayPrimary,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .graphicsLayer(
                        translationY = if (index == selectedIndex) -4F else 0F,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .width(200.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = name,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
        }
    }
}

@Composable
fun SliderBoxComponentVertical(names: List<String>, modifier: Modifier = Modifier) {
    var selectedIndex by remember { mutableIntStateOf(0) }

    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
    ) {
        itemsIndexed(names) { index, name ->
            Box(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.background)
                    .clickable {
                        selectedIndex = index
                    }
                    .border(
                        width = 2.dp,
                        color = GrayPrimary,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .graphicsLayer(
                        translationY = if (index == selectedIndex) -4F else 0F,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .height(100.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 40.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .fillMaxHeight(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_background),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp)),
                            alignment = Alignment.BottomEnd
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}



