package com.develop.nowasteinmyfridge.domain

import com.develop.nowasteinmyfridge.data.repository.FirebaseFirestoreRepository
import javax.inject.Inject

class ClearGroceryListUseCase @Inject constructor(
    private val firestoreRepository: FirebaseFirestoreRepository
) {
    suspend operator fun invoke() {
        firestoreRepository.clearGroceryList()
    }
}
