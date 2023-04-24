package com.develop.nowasteinmyfridge.api

import com.develop.nowasteinmyfridge.model.MealResponse
import com.develop.nowasteinmyfridge.utill.Constant.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NoWasteInMyFridgeService {

    @GET("filter.php")
    suspend fun getMeal(
    @Query("a")
    area: String = "Canadian"
    ):MealResponse


    companion object{

        fun create():NoWasteInMyFridgeService =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NoWasteInMyFridgeService::class.java)
    }
}