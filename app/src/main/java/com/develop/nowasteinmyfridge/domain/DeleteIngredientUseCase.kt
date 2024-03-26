package com.develop.nowasteinmyfridge.domain

import com.develop.nowasteinmyfridge.data.repository.FirebaseFirestoreRepository
import javax.inject.Inject

class DeleteIngredientUseCase @Inject constructor(
    private val firestoreRepository: FirebaseFirestoreRepository
) {
    suspend fun deleteIngredient(ingredientName: String) {
        firestoreRepository.deleteIngredient(ingredientName)
    }
}