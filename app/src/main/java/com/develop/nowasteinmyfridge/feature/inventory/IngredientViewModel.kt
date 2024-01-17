package com.develop.nowasteinmyfridge.feature.inventory

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.develop.nowasteinmyfridge.data.model.Ingredient
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class DataViewModel : ViewModel() {
    val state = mutableStateOf<List<Ingredient>>(emptyList())

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            state.value = getDataFromFireStore()
        }
    }

    fun pushDataToFirestore(name: String, quantity: Int, image: String, mfg: String, efd: String) {
        viewModelScope.launch {
            val newIngredient = Ingredient(name, quantity, image, mfg, efd)
            postDataToFireStore(newIngredient)
        }
    }

    suspend fun getDataFromFireStore(): List<Ingredient> {
        val db = FirebaseFirestore.getInstance()
        var ingredients = emptyList<Ingredient>()

        try {
            val querySnapshot = db.collection("ingredient").get().await()
            ingredients = querySnapshot.toObjects(Ingredient::class.java)
        } catch (e: FirebaseFirestoreException) {
            Log.d("error", "getDataFromFireStore: $e")
        }

        return ingredients
    }


    suspend fun postDataToFireStore(ingredient: Ingredient) {
        val db = FirebaseFirestore.getInstance()

        try {
            db.collection("ingredient")
                .add(ingredient)
                .await()
        } catch (e: FirebaseFirestoreException) {
            Log.d("error", "postDataToFireStore: $e")
        }
    }
}
