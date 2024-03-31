package com.develop.nowasteinmyfridge.data.model

data class Product(
    val product: ProductDetails = ProductDetails()
)

data class ProductDetails(
    val brands: String = ""
)