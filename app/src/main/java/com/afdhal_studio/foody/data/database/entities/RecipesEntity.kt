package com.afdhal_studio.foody.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.afdhal_studio.foody.models.FoodRecipe
import com.afdhal_studio.foody.util.Constants.Companion.RECIPES_TABLE

/**
 *Created by Muh Fuad Afdhal on 02/05/2021
 */

@Entity(tableName = RECIPES_TABLE)
data class RecipesEntity(
    var foodRecipe: FoodRecipe

) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}