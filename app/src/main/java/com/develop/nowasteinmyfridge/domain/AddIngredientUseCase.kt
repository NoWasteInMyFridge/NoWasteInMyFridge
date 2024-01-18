package com.develop.nowasteinmyfridge.domain

import com.develop.nowasteinmyfridge.data.model.Ingredient
import com.develop.nowasteinmyfridge.data.repository.FirebaseFirestoreRepository
import javax.inject.Inject

class AddIngredientUseCase @Inject constructor(
    private val firestoreRepository: FirebaseFirestoreRepository
) {
    suspend operator fun invoke(ingredient: Ingredient) {
        firestoreRepository.addIngredient(ingredient)
    }
}