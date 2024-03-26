package com.develop.nowasteinmyfridge.data.model

import android.net.Uri

data class IngredientCreate(
    val name: String = "",
    val quantity: Int = 0,
    val image: Uri? = null,
    val mfg: String = "",
    val efd: String = "",
    val inFreeze: Boolean = false,
)
