package com.develop.nowasteinmyfridge.feature.adding

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.develop.nowasteinmyfridge.data.model.IngredientCreate
import com.develop.nowasteinmyfridge.domain.AddIngredientUseCase
import com.develop.nowasteinmyfridge.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddingViewModel @Inject constructor(
    private val addIngredientUseCase: AddIngredientUseCase
) : ViewModel() {
    private val _addIngredientResult = MutableStateFlow<Result<Unit>?>(null)
    val addIngredientResult: StateFlow<Result<Unit>?> get() = _addIngredientResult

    fun addIngredient(ingredient: IngredientCreate) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _addIngredientResult.value = Result.Loading
                addIngredientUseCase.invoke(ingredient = ingredient)
                _addIngredientResult.value = Result.Success(Unit)
            }
            catch (e:Exception){
                _addIngredientResult.value = Result.Error(e)
                Log.e("","",e)
            }
        }
    }
}