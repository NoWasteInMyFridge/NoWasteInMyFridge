
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.develop.nowasteinmyfridge.R
import com.develop.nowasteinmyfridge.feature.home.HomeViewModel
import com.develop.nowasteinmyfridge.feature.inventory.InventoryViewModel
import com.develop.nowasteinmyfridge.ui.theme.BaseColor
import com.develop.nowasteinmyfridge.ui.theme.Black
import com.develop.nowasteinmyfridge.ui.theme.GrayPrimary
import com.develop.nowasteinmyfridge.ui.theme.White
import java.text.SimpleDateFormat
import java.util.Locale


// Imports removed for brevity
@SuppressLint("SuspiciousIndentation", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    inventoryViewModel: InventoryViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val ingredientsList by inventoryViewModel.ingredientsState
    val displayedIngredients = ingredientsList.map { ingredient ->
        "${ingredient.name} ${ingredient.efd} ${ingredient.image}"
    }
    val sortedDisplayedIngredients = ingredientsList.sortedBy { ingredient ->
        val dateString = ingredient.efd // Assuming efd is the date string
        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(dateString)
    }


    val ingredientsForSearch = sortedDisplayedIngredients
        .take(1)
        .map { it.name } // Add prefix "Ingredient: " to each name
        .joinToString(", ")

// Now ingredientsForSearchString contains strings like "Ingredient: Onion, Ingredient: Tomato, Ingredient: Potato"


    val recipesState by homeViewModel.recipesState
    var isApiRequestCompleted = false

    if (ingredientsForSearch.isNotEmpty()) {
        LaunchedEffect(Unit) {
            try {
                // Call the searchRecipes function with the desired query
                homeViewModel.searchRecipes(ingredientsForSearch)
                Log.d("IngredientsForSearch", ingredientsForSearch)
//                isApiRequestCompleted = true
            } catch (e: Exception) {
                // Handle exceptions if the API call fails
                Log.e("API Request Failed", e.message ?: "Unknown error")
            }
        }
    } else {
        Log.e("error from LaunchedEffect", "Ingredients for search is empty")
    }

// Wait until the API request is completed before logging or handling recipesState
//    if (isApiRequestCompleted) {
    Log.d("this is response", recipesState.toString())
//    }


//        LaunchedEffect(Unit) {
//            // Call the searchRecipes function with the desired query
//            homeViewModel.searchRecipes(ingredientsForSearch)
//            Log.d("IngredientsForSearch", ingredientsForSearch)
//            Log.d("this si response", recipesState.toString())
////            Log.d("HomeScreen", "Type of ingredientsForSearch: ${ingredientsForSearch::class.java.simpleName}")
//        }
//    } else {
//        Log.e("error from LaunchedEffect", "Ingredients for search is empty")
//    }


//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.White)
//            .padding(16.dp)
//    ) {
//        // Display the state of recipes
    val hits = recipesState?.hits ?: emptyList() // Use safe call operator to handle null value
    val recipeNames = hits.map { it.recipe.label } // Extract labels from hits
    val recipeImages = hits.map { it.recipe.image } // Extract image URLs from hits

//
//        if (hits != null) {
//            hits.forEach { hit ->
//                val recipe = hit.recipe
//                Text(
//                    text = "Recipe Label: ${recipe.label}",
//                    modifier = Modifier.padding(bottom = 4.dp)
//                )
//                Text(
//                    text = "Image URL: ${recipe.image}",
//                    modifier = Modifier.padding(bottom = 8.dp)
//                )
//            }
//        } else {
//            Text(text = "Loading...")
//        }
//    }

    Column {
//        Text(text = "Ingredients List: $sortedDisplayedIngredients")

        // Header section
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
                    // Header image and greeting
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

                    // SliderBoxComponent
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
                            val hits = recipesState?.hits
                            SliderBoxComponentVertical(
                                names = recipeNames,
                                images = recipeImages
                            )
//                            SliderBoxComponentVertical(
//                                names = listOf(
//                                    "Salad", "Apple Pie", "Cocopop", "Muchupichu",
//                                    "Apple Pie", "Cocopop", "Muchupichu"
//                                )
//                            )
                        }
                    }
                }
            }
        }
    }

}

@OptIn(ExperimentalComposeUiApi::class)
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Composable
//fun HomeScreen(
//    inventoryViewModel: InventoryViewModel = hiltViewModel(),
//    homeViewModel: HomeViewModel = hiltViewModel(),
//) {
//    // Fetch ingredients list
//    val ingredientsList by inventoryViewModel.ingredientsState
//    val displayedIngredients = ingredientsList.map { ingredient ->
//        "${ingredient.name} ${ingredient.efd} ${ingredient.image}"
//    }
//    val sortedDisplayedIngredients = displayedIngredients.sortedBy { ingredientString ->
//        val dateString = ingredientString.substringAfterLast(" ")
//        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(dateString)
//    }.map { ingredientString ->
//        ingredientString.substringBeforeLast(" ")
//    }
//    val getRecipeResult by homeViewModel.recipes.collectAsStateWithLifecycle()
////    val BASE_URL = "https://api.edamam.com/api/"
////
////    val retrofit = remember {
////        Retrofit.Builder()
////            .baseUrl(BASE_URL)
////            .addConverterFactory(GsonConverterFactory.create())
////            .build()
////    }
////    val edamamApi = retrofit.create(EdamamApi::class.java)
//
//    // State for holding recipes
//    var recipes by remember { mutableStateOf<RecipeSearchResponse?>(null) }
//    rememberCoroutineScope()
//
//    // Fetch recipes when ingredients change
//    LaunchedEffect(sortedDisplayedIngredients) {
//        val query = sortedDisplayedIngredients.take(3).joinToString(", ")
//        homeViewModel.searchRecipes(query)
//
////        val response = edamamApi.searchRecipes(
////            query = query,
////            appId = "914387a2",
////            appKey = "a4bae4862aea9151811f6d12bb6e35bb"
////        )
//            recipes = getRecipeResult
//    }
//    val recipeNames = recipes?.hits?.map { it.recipe.label } ?: listOf()
//    val recipeImages = recipes?.hits?.map { it.recipe.image } ?: listOf()
//
//    // UI Layout
//    Column {
////        Text(text = "Ingredients List: $sortedDisplayedIngredients")
//
//        // Header section
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(bottom = 48.dp)
//                .background(color = BaseColor)
//        ) {
//            Scaffold {
//                Column(
//                    modifier = Modifier.fillMaxSize(),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.SpaceBetween
//                ) {
//                    // Header image and greeting
//                    Box(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .fillMaxHeight(fraction = 0.2f)
//                    ) {
//                        Image(
//                            painter = painterResource(id = R.mipmap.home_topper),
//                            contentDescription = "",
//                            modifier = Modifier.fillMaxSize(),
//                            contentScale = ContentScale.FillBounds
//                        )
//                        Column(
//                            modifier = Modifier
//                                .wrapContentSize()
//                                .align(Alignment.CenterStart)
//                                .padding(start = 20.dp, top = 16.dp)
//                        ) {
//                            Text(
//                                text = buildAnnotatedString {
//                                    for (letter in "Hi, Ampere") {
//                                        withStyle(
//                                            style = SpanStyle(
//                                                shadow = Shadow(
//                                                    color = Black,
//                                                    offset = Offset(2f, 2f),
//                                                    blurRadius = 4f
//                                                )
//                                            )
//                                        ) {
//                                            append(letter.toString())
//                                        }
//                                    }
//                                },
//                                style = MaterialTheme.typography.headlineMedium,
//                                color = White
//                            )
//                        }
//                        Column(
//                            modifier = Modifier
//                                .wrapContentSize()
//                                .align(Alignment.BottomStart)
//                                .padding(start = 20.dp, bottom = 10.dp)
//                        ) {
//                            Text(
//                                text = "Ingredients",
//                                style = MaterialTheme.typography.bodyLarge,
//                                color = Black
//                            )
//                        }
//                    }
//
//                    // SliderBoxComponent
//                    Box(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .background(color = BaseColor)
//                    ) {
//                        Column(
//                            modifier = Modifier
//                                .fillMaxSize()
//                                .fillMaxWidth()
//                                .padding(16.dp)
//                        ) {
//                            SliderBoxComponent(names = listOf("Apple", "Pork", "Peach", "pie"))
//                            Spacer(modifier = Modifier.height(16.dp))
//                            Text(
//                                text = "Today Option",
//                                style = MaterialTheme.typography.titleMedium,
//                                color = Black
//                            )
//                            Spacer(modifier = Modifier.height(16.dp))
//                            SliderBoxComponentVertical(
//                                names = recipeNames,
//                                images = recipeImages
//                            )
////                            SliderBoxComponentVertical(
////                                names = listOf(
////                                    "Salad", "Apple Pie", "Cocopop", "Muchupichu",
////                                    "Apple Pie", "Cocopop", "Muchupichu"
////                                )
////                            )
//                        }
//                    }
//                }
//            }
//        }
//
//        // Display recipes
////        recipes?.let { recipeResponse ->
////            LazyColumn {
////                items(recipeResponse.hits.size) { index ->
////                    val hit = recipeResponse.hits[index]
////                    val recipe = hit.recipe
////                    Column(
////                        modifier = Modifier
////                            .padding(8.dp)
////                            .fillMaxWidth()
////                    ) {
////                        Text(
////                            text = "Recipe: ${recipe.label}",
////                            fontWeight = FontWeight.Bold,
////                            fontSize = 18.sp,
////                            modifier = Modifier.padding(bottom = 4.dp)
////                        )
////                        if (recipe.url.isNotEmpty()) {
////                            Text(
////                                text = "URL: ${recipe.url}",
////                                fontStyle = FontStyle.Italic,
////                                fontSize = 14.sp
////                            )
////                        }
////                        if (recipe.image.isNotEmpty()) {
////                            AsyncImage(
////                                model = recipe.image,
//////                                placeholder = painterResource(id = R.drawable.sudoimage),
//////                                error = painterResource(id = R.drawable.sudoimage),
////                                contentDescription = "The delasign logo",
////                            )
//////                            Image(
//////                                painter = rememberImagePainter(recipe.image),
//////                                contentDescription = null,
//////                                modifier = Modifier
//////                                    .size(200.dp)
//////                                    .padding(vertical = 8.dp)
//////                            )
////                        }
////                    }
////                }
////            }
////        }
//    }
//}

@Composable
fun SliderBoxComponent(names: List<String>, modifier: Modifier = Modifier) {
    var selectedIndex by remember { mutableStateOf(0) }

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
fun SliderBoxComponentVertical(
    names: List<String>,
    images: List<String>, // List of image URLs
    modifier: Modifier = Modifier
) {
    var selectedIndex by remember { mutableStateOf(0) }

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
                    Box( modifier = Modifier
                        .fillMaxHeight()
                        .width(250.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = name,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onBackground,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
//                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                    ) {
                        AsyncImage(
                            model = images.getOrNull(index),
                            contentDescription = "Image for $name",
                            modifier = Modifier
                                .fillMaxSize()
//                                .clip(RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp))
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
    HomeScreen()
}
