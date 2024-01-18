package com.develop.nowasteinmyfridge.data.repository

import android.util.Log
import com.develop.nowasteinmyfridge.data.model.Ingredient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseFirestoreRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth,
) : FirebaseFirestoreRepository {
    private val db = firebaseFirestore
    private val userId = firebaseAuth.currentUser?.uid.orEmpty()
    override suspend fun getIngredient(): List<Ingredient> {
        var ingredients = emptyList<Ingredient>()
        try {
            val querySnapshot = db.collection("user/$userId/ingredients").get().await()
            ingredients = querySnapshot.toObjects(Ingredient::class.java)
        } catch (e: FirebaseFirestoreException) {
            Log.d("error", "getIngredient: $e")
        }
        return ingredients
    }

    override suspend fun addIngredient(ingredient: Ingredient) {
        try {
            db.collection("user/$userId/ingredients")
                .add(ingredient)
                .await()
        } catch (e: FirebaseFirestoreException) {
            Log.d("error", "postDataToFireStore: $e")
        }
    }
}