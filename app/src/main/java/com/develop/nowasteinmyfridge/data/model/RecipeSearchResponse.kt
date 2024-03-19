package com.develop.nowasteinmyfridge.data.model

data class RecipeSearchResponse(
    val hits: List<RecipeHit> = emptyList()
)
