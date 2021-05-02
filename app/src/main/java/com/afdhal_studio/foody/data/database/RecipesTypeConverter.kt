package com.afdhal_studio.foody.data.database

import androidx.room.TypeConverter
import com.afdhal_studio.foody.models.FoodRecipe
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 *Created by Muh Fuad Afdhal on 02/05/2021
 */

class RecipesTypeConverter {
    var gson = Gson()

    @TypeConverter
    fun foodRecipeToString(foodRecipe: FoodRecipe): String {
        return gson.toJson(foodRecipe)
    }

    @TypeConverter
    fun stringToRecipe(data: String): FoodRecipe {
        val listType = object : TypeToken<FoodRecipe>() {}.type
        return gson.fromJson(data, listType)
    }
}