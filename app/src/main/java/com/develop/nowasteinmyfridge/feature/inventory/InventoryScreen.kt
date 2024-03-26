package com.develop.nowasteinmyfridge.feature.inventory

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@SuppressLint("SuspiciousIndentation", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun InventoryScreen(
    inventoryViewModel: InventoryViewModel = hiltViewModel()
) {
    val ingredientsList by inventoryViewModel.ingredientsState
    var selectedIngredientIndex by remember { mutableStateOf(-1) }
    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(80.dp),
                Alignment.BottomCenter
            ) {
                Text(
                    text = "Inventory",
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
            }
            Column(
                modifier = Modifier.padding(top = 16.dp)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    contentPadding = PaddingValues(16.dp),
                ) {
                    if (ingredientsList.isNotEmpty()) {
                        items(ingredientsList.size) { index ->
                            val ingredient = ingredientsList[index]
                            ShowingBox(
                                ingredient = ingredient,
                                onDeleteClicked = {
                                    inventoryViewModel.deleteIngredient(ingredient.name)
                                    // Handle deletion here
//                                    onDeleteIngredient(index)
                                }
                            )
                                selectedIngredientIndex = index
                        }
                    } else {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("No ingredients available.")
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun InventoryScreenPreview() {
    InventoryScreen()
}
