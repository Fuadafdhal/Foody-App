package com.afdhal_studio.foody.data.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.afdhal_studio.foody.models.FoodJoke
import com.afdhal_studio.foody.util.Constants.Companion.FOOD_JOKE_TABLE

/**
 *Created by Muh Fuad Afdhal on 12/05/2021
 */

@Entity(tableName = FOOD_JOKE_TABLE)
class FoodJokeEntity(
    @Embedded
    var foodJoke: FoodJoke
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}