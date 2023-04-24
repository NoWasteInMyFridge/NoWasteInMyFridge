package com.develop.nowasteinmyfridge.viewmodels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.develop.nowasteinmyfridge.Repository.NoWasteInMyFridgeRepository
import com.develop.nowasteinmyfridge.model.Meal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class NoWasteInMyFrideViewModel @Inject constructor
    (private val noWasteInMyFridgeRepository: NoWasteInMyFridgeRepository):ViewModel() {
    private val _meal = MutableLiveData<List<Meal>>()

    val meal:LiveData<List<Meal>> = _meal

    init {
        viewModelScope.launch{
            try{
                val mealList = noWasteInMyFridgeRepository.getMealList()
                _meal.value = mealList
            }catch (e:Exception){
                println(e.toString())
                _meal.value = emptyList()
            }


        }
    }
}