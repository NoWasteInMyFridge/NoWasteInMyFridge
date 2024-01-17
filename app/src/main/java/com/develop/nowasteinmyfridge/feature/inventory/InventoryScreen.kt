package com.develop.nowasteinmyfridge.feature.inventory

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun InventoryScreen(
    dataViewModel: DataViewModel = viewModel()
) {
    val ingredientsList = dataViewModel.state.value

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(ingredientsList.size) { index ->
            val ingredient = ingredientsList[index]
            ShowingBox(ingredient = ingredient)
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun InventoryScreenPreview() {
    InventoryScreen()
}
