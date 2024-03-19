package com.develop.nowasteinmyfridge.domain

import com.develop.nowasteinmyfridge.data.model.RecipeSearchResponse
import com.develop.nowasteinmyfridge.data.repository.RecipeRepository
import javax.inject.Inject

class GetRecipeUseCase @Inject constructor(
    private val recipeRepository: RecipeRepository
) {
    suspend operator fun invoke(query: String): RecipeSearchResponse {
        return recipeRepository.searchRecipes(query)
    }
}