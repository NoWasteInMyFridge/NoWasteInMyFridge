package com.develop.nowasteinmyfridge.data.repository

import android.util.Log
import com.develop.nowasteinmyfridge.data.model.Ingredient
import com.develop.nowasteinmyfridge.data.model.IngredientCreate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseFirestoreRepositoryImpl @Inject constructor(
    firebaseFirestore: FirebaseFirestore,
    firebaseAuth: FirebaseAuth,
    firebaseStorage: FirebaseStorage,
) : FirebaseFirestoreRepository {
    private val db = firebaseFirestore
    private val userId = firebaseAuth.currentUser?.uid.orEmpty()
    private val storageRef = firebaseStorage.reference
    override suspend fun getIngredient(): List<Ingredient> {
        var ingredients = emptyList<Ingredient>()
        try {
            val querySnapshot = db.collection("users/$userId/ingredients").get().await()
            ingredients = querySnapshot.toObjects(Ingredient::class.java)
        } catch (e: FirebaseFirestoreException) {
            Log.d("error", "getIngredient: $e")
        }
        return ingredients
    }

    override suspend fun addIngredient(ingredient: IngredientCreate) {
        try {
            val image = ingredient.image
            if (image != null) {
                val ref = storageRef.child("users/$userId/ingredients/${image.lastPathSegment}")
                val uploadTask = ref.putFile(image)
                try {
                    val taskSnapshot = uploadTask.await()
                    val imageUrl = taskSnapshot.metadata!!.reference!!.downloadUrl.await().toString()
                    db.collection("users/$userId/ingredients")
                        .add(
                            Ingredient(
                                name = ingredient.name,
                                quantity = ingredient.quantity,
                                image = imageUrl,
                                mfg = ingredient.mfg,
                                efd = ingredient.efd,
                            )
                        ).await()
                } catch (uploadException: Exception) {
                    Log.e("ImageUpload", "Error uploading image: $uploadException")
                }
            }else{
                db.collection("users/$userId/ingredients")
                    .add(ingredient)
                    .await()
            }
        } catch (e: FirebaseFirestoreException) {
            Log.d("FirestoreError", "Error adding ingredient to Firestore: $e")
        }
    }
}