package com.develop.nowasteinmyfridge.feature.home

import RecipeSearchResponse
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.develop.nowasteinmyfridge.domain.GetRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRecipeUseCase: GetRecipeUseCase,
) : ViewModel() {
    private val _recipesState = mutableStateOf<RecipeSearchResponse>(RecipeSearchResponse(emptyList()))
    val recipesState: State<RecipeSearchResponse>
        get() = _recipesState


    fun searchRecipes(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = getRecipeUseCase(query)
                _recipesState.value = response
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error fetching recipes", e)
            }
        }
    }
}
