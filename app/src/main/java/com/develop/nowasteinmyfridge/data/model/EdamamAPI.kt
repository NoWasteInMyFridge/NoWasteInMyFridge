package com.develop.nowasteinmyfridge.data.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EdamamApi {
    @GET("recipes/v2?type=public")
    suspend fun searchRecipes(
        @Query("q") query: String,
        @Query("app_id") appId: String,
        @Query("app_key") appKey: String
    ): Response<RecipeSearchResponse>
}