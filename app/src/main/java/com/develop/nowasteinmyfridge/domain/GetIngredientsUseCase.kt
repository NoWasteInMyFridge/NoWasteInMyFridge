package com.develop.nowasteinmyfridge.domain

import com.develop.nowasteinmyfridge.data.model.Ingredient
import com.develop.nowasteinmyfridge.data.repository.FirebaseFirestoreRepository
import javax.inject.Inject

class GetIngredientsUseCase @Inject constructor(
    private val firestoreRepository: FirebaseFirestoreRepository,
) {
    suspend operator fun invoke(): List<Ingredient> {
        return firestoreRepository.getIngredient()
    }
}