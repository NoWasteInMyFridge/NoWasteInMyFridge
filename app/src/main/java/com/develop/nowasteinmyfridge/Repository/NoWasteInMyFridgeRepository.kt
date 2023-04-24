package com.develop.nowasteinmyfridge.Repository

import com.develop.nowasteinmyfridge.api.NoWasteInMyFridgeService
import com.develop.nowasteinmyfridge.model.Meal
import javax.inject.Inject

class NoWasteInMyFridgeRepository @Inject constructor
    (private val noWasteInMyFridgeService: NoWasteInMyFridgeService) {
    suspend fun getMealList():List<Meal>{
        val response = noWasteInMyFridgeService.getMeal()
        return response.meals
    }
}