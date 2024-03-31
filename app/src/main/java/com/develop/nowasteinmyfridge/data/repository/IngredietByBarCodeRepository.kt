package com.develop.nowasteinmyfridge.data.repository

import com.develop.nowasteinmyfridge.data.model.Product

interface IngredietByBarCodeRepository {
    suspend fun getIngredientByBracode(ingredientQrcodeID: String): Product
}
