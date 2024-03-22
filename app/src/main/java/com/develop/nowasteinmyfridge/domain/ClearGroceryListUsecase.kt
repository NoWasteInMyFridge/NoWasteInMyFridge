package com.develop.nowasteinmyfridge.domain

import com.develop.nowasteinmyfridge.data.repository.FirebaseFirestoreRepository
import javax.inject.Inject

class ClearGroceryListUsecase @Inject constructor(
    private val firestoreRepository: FirebaseFirestoreRepository
) {
    suspend fun clearGroceryList() {
        firestoreRepository.clearGroceryList()
    }
}
