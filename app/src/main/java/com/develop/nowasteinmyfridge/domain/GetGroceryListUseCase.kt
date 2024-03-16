package com.develop.nowasteinmyfridge.domain

import com.develop.nowasteinmyfridge.data.model.GroceryList
import com.develop.nowasteinmyfridge.data.repository.FirebaseFirestoreRepository
import javax.inject.Inject

class GetGroceryListUseCase @Inject constructor(
    private val firestoreRepository: FirebaseFirestoreRepository,
) {
    suspend operator fun invoke(): List<GroceryList> {
        return firestoreRepository.getGroceryList()
    }
}