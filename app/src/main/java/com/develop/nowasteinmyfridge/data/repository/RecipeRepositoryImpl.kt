

import com.develop.nowasteinmyfridge.data.repository.RecipeRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import javax.inject.Inject

const val APP_ID_EDAMANAPI = "914387a2"
const val APP_KEY_EDAMANAPI = "a4bae4862aea9151811f6d12bb6e35bb"

class RecipeRepositoryImpl @Inject constructor() : RecipeRepository {
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
            appKey = APP_KEY_EDAMANAPI
        ).body() ?: throw IOException("Error fetching recipes")
    }
}
