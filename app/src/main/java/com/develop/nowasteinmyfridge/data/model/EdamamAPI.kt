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

// RecipeSearchResponse.kt
data class RecipeSearchResponse(
    val hits: List<RecipeHit>
)

data class RecipeHit(
    val recipe: Recipe
)

data class Recipe(
    val label: String,
    val url: String,
    val image: String
)