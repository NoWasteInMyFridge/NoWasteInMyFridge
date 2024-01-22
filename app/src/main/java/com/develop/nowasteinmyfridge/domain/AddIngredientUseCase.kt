package com.develop.nowasteinmyfridge.domain

import com.develop.nowasteinmyfridge.data.model.IngredientCreate
import com.develop.nowasteinmyfridge.data.repository.FirebaseFirestoreRepository
import javax.inject.Inject

class AddIngredientUseCase @Inject constructor(
    private val firestoreRepository: FirebaseFirestoreRepository
) {
    suspend operator fun invoke(ingredient: IngredientCreate) {
        firestoreRepository.addIngredient(ingredient)
    }
}