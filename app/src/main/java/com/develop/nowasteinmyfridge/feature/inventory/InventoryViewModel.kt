package com.develop.nowasteinmyfridge.feature.inventory

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.develop.nowasteinmyfridge.data.model.Ingredient
import com.develop.nowasteinmyfridge.domain.GetIngredientsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InventoryViewModel @Inject constructor(
    private val getIngredientsUseCase: GetIngredientsUseCase,
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
}
