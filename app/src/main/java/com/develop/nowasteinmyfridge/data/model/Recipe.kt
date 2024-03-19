package com.develop.nowasteinmyfridge.data.model

data class Recipe(
    val label: String,
    val url: String,
    val image: String,
    val ingredientLines: List<String>,
)
