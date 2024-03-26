package com.develop.nowasteinmyfridge.data.model

data class Ingredient(
    val id: String = "",
    val name: String = "",
    val quantity: Int = 0,
    val image:String ="",
    val mfg:String ="",
    val efd:String ="",
    val isInFreezer: Boolean = false,
)