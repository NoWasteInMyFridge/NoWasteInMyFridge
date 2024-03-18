package com.develop.nowasteinmyfridge.data.repository

import RecipeSearchResponse

interface RecipeRepository {
    suspend fun searchRecipes(query: String): RecipeSearchResponse
}