package com.develop.nowasteinmyfridge.data.repository

import com.develop.nowasteinmyfridge.data.model.Ingredient
import com.develop.nowasteinmyfridge.data.model.IngredientCreate

interface FirebaseFirestoreRepository {
    suspend fun getIngredient(): List<Ingredient>
    suspend fun addIngredient(ingredient: IngredientCreate)
}