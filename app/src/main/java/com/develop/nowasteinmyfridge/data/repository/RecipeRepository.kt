package com.develop.nowasteinmyfridge.data.repository

import com.develop.nowasteinmyfridge.data.model.RecipeSearchResponse


interface RecipeRepository {
    suspend fun searchRecipes(query: String): RecipeSearchResponse
}