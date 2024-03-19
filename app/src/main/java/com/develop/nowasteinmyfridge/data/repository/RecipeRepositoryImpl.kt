package com.develop.nowasteinmyfridge.data.repository

import com.develop.nowasteinmyfridge.data.model.EdamamApi
import com.develop.nowasteinmyfridge.data.model.RecipeSearchResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import javax.inject.Inject


class RecipeRepositoryImpl @Inject constructor() : RecipeRepository {
    private val APP_ID_EDAMANAPI = System.getenv("APP_ID_EDAMANAPI")
    private val APP_KEY_EDAMANAPI = System.getenv("APP_KEY_EDAMANAPI")
    private val BASE_URL = "https://api.edamam.com/api/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val edamamApi = retrofit.create(EdamamApi::class.java)

    override suspend fun searchRecipes(query: String): RecipeSearchResponse {
        return edamamApi.searchRecipes(
            query = query,
            appId = APP_ID_EDAMANAPI,
            appKey = APP_KEY_EDAMANAPI,
        ).body() ?: throw IOException("Error fetching recipes")
    }
}
