package com.afdhal_studio.foody.data

import com.afdhal_studio.foody.data.network.FoodRecipesApi
import com.afdhal_studio.foody.models.FoodRecipe
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodRecipesApi: FoodRecipesApi
) {

    suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipe> {
        return foodRecipesApi.getRecipes(queries)
    }

}