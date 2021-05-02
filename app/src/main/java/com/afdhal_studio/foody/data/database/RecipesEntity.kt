package com.afdhal_studio.foody.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.afdhal_studio.foody.models.FoodRecipe
import com.afdhal_studio.foody.util.Constants

/**
 *Created by Muh Fuad Afdhal on 02/05/2021
 */

@Entity(tableName = Constants.RECIPES_TABLE)
data class RecipesEntity(
    var foodRecipe: FoodRecipe

) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}