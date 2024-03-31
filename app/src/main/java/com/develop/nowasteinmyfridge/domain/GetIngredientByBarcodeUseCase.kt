package com.develop.nowasteinmyfridge.domain

import com.develop.nowasteinmyfridge.data.model.Product
import com.develop.nowasteinmyfridge.data.repository.IngredietByBarCodeRepository
import javax.inject.Inject

class GetIngredientByBarcodeUseCase @Inject constructor(
    private val providesGetBarCodeRepository: IngredietByBarCodeRepository
) {
    suspend operator fun invoke(ingredientQrcodeID: String): Product {
        return providesGetBarCodeRepository.getIngredientByBracode(ingredientQrcodeID)
    }
}

