package com.develop.nowasteinmyfridge.feature.inventory

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.develop.nowasteinmyfridge.data.model.Ingredient
import com.develop.nowasteinmyfridge.domain.DeleteIngredientUseCase
import com.develop.nowasteinmyfridge.domain.GetIngredientsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InventoryViewModel @Inject constructor(
    private val getIngredientsUseCase: GetIngredientsUseCase,
    private val deleteIngredientUseCase: DeleteIngredientUseCase
) : ViewModel() {
    private val _ingredientsState = mutableStateOf<List<Ingredient>>(emptyList())
    val ingredientsState: State<List<Ingredient>>
        get() = _ingredientsState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getIngredients()
        }
    }

    private suspend fun getIngredients() {
        _ingredientsState.value = getIngredientsUseCase.invoke()
    }

    fun deleteIngredient(ingredientName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val ingredientToDelete = _ingredientsState.value.find { it.name == ingredientName }
                ingredientToDelete?.let { ingredient ->
                    deleteIngredientUseCase.deleteIngredient(ingredient.name)
                }
                getIngredients()
            } catch (e: Exception) {
                Log.e("","",e)
            }
        }
    }
}
