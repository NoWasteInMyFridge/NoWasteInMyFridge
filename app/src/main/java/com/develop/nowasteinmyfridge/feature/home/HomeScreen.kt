package com.develop.nowasteinmyfridge.feature.home

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.develop.nowasteinmyfridge.R
import com.develop.nowasteinmyfridge.feature.inventory.InventoryViewModel
import com.develop.nowasteinmyfridge.ui.theme.BaseColor
import com.develop.nowasteinmyfridge.ui.theme.BaseGray
import com.develop.nowasteinmyfridge.ui.theme.Black
import com.develop.nowasteinmyfridge.ui.theme.White
import java.text.SimpleDateFormat
import java.util.Locale

@SuppressLint("SuspiciousIndentation", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    inventoryViewModel: InventoryViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel(),
    navController: NavController,
) {
    val ingredientsList by inventoryViewModel.ingredientsState
    val displayedIngredients = ingredientsList.map { ingredient ->
        ingredient.name to ingredient.image
    }

    val names = displayedIngredients.map { it.first }
    val images = displayedIngredients.map { it.second }

    val sortedDisplayedIngredients = ingredientsList.sortedBy { ingredient ->
        val dateString = ingredient.efd
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(dateString)
    }
    val ingredientsForSearch = sortedDisplayedIngredients
        .take(1)
        .map { it.name }
        .joinToString(", ")

    val recipesState by homeViewModel.recipesState
    if (ingredientsForSearch.isNotEmpty()) {
        LaunchedEffect(Unit) {
            try {
                homeViewModel.searchRecipes(ingredientsForSearch)
                Log.d("IngredientsForSearch", ingredientsForSearch)
            } catch (e: Exception) {
                Log.e("API Request Failed", e.message ?: "Unknown error")
            }
        }
    } else {
        Log.e("error from LaunchedEffect", "Ingredients for search is empty")
    }

    val hits = recipesState?.hits
    val recipeNames = hits?.map { it.recipe.label }
    val recipeImages = hits?.map { it.recipe.image }
    val ingredientLines = hits?.map { it.recipe.ingredientLines } ?: emptyList()
    if (hits != null) {
        Log.d("Recipes", "${hits}")
        Log.d("recipeNames", "${recipeNames}")
        Log.d("ingredientLines", "${ingredientLines}")
    // Use debug level for informational logs
    } else {
        Log.w("Recipes", "No recipes found") // Use warning level for empty results
    }

    Column {
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
                            painter = painterResource(id = R.mipmap.home_topper),
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
                            SliderBoxComponent(names = names, images = images)
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Today Option",
                                style = MaterialTheme.typography.titleMedium,
                                color = Black
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            recipeNames?.let { names ->
                                recipeImages?.let { images ->
                                    ingredientLines?.let { lines ->
                                        Log.d("sorryyy", "${lines.joinToString(",")}")
                                        SliderBoxComponentVertical(
                                            names = names ?: emptyList(),
                                            images = images ?: emptyList(),
                                            ingredientLines = lines ?: emptyList(),
                                            onItemClick = { name, image, lines ->
                                                navController.navigate("menu/${Uri.encode(name)}/${Uri.encode(image)}/${Uri.encode(lines.joinToString(","))}")
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun SliderBoxComponent(
    names: List<String>,
    images: List<String>,
    modifier: Modifier = Modifier
) {
    var selectedIndex by remember { mutableStateOf(0) }
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .height(84.dp)
    ) {
        itemsIndexed(names) { index, name ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 8.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.background)
                    .clickable {
                        selectedIndex = index
                    }
                    .width(200.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = images.getOrNull(index),
                        contentDescription = "Image for $name",
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.FillBounds
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
                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp) // Adjust the height of the shadow
                        .align(Alignment.BottomCenter)
                ) {
                    drawIntoCanvas { canvas ->
                        val shadowColor = BaseGray
                        val shadowAlpha = 0.25f
                        val shadowHeight = size.height
                        drawRect(
                            color = BaseGray.copy(alpha = 0.25f),
                            topLeft = Offset(0f, 0f),
                            size = Size(size.width, size.height)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SliderBoxComponentVertical(
    names: List<String>,
    images: List<String>,
    ingredientLines: List<List<String>>,
    modifier: Modifier = Modifier,
    onItemClick: (String, String, List<String>) -> Unit
) {
    var selectedIndex by remember { mutableStateOf(0) }

    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        itemsIndexed(names) { index, name ->
            Box(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.background)
                    .clickable {
                        selectedIndex = index
                        onItemClick(name, images.getOrNull(index) ?: "", ingredientLines[index])
                    }
                    .height(100.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(250.dp)
                            .padding(end = 16.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = name,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onBackground,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier.fillMaxHeight(),
                    ) {
                        AsyncImage(
                            model = images.getOrNull(index),
                            contentDescription = "Image for $name",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds,
                        )
                    }
                }
                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .align(Alignment.BottomCenter)
                ) {
                    drawIntoCanvas {
                        drawRect(
                            color = BaseGray.copy(alpha = 0.25f),
                            topLeft = Offset(0f, 0f),
                            size = Size(size.width, size.height),
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}
