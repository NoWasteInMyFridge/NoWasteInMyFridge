package com.develop.nowasteinmyfridge.domain

import com.develop.nowasteinmyfridge.data.model.GroceryListCreate
import com.develop.nowasteinmyfridge.data.repository.FirebaseFirestoreRepository
import javax.inject.Inject

class AddGroceryListUseCase @Inject constructor(
    private val firestoreRepository: FirebaseFirestoreRepository
) {
    suspend operator fun invoke(groceryList: GroceryListCreate) {
        firestoreRepository.addGroceryList(groceryList)
    }
}