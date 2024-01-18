package com.develop.nowasteinmyfridge.feature.adding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.develop.nowasteinmyfridge.data.model.Ingredient
import com.develop.nowasteinmyfridge.domain.AddIngredientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddingViewModel @Inject constructor(
    private val addIngredientUseCase: AddIngredientUseCase
) : ViewModel() {

    fun addIngredient(name: String, quantity: Int, image: String, mfg: String, efd: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val newIngredient = Ingredient(name, quantity, image, mfg, efd)
            addIngredientUseCase.invoke(ingredient = newIngredient)
        }
    }
}