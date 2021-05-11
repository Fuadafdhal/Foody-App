package com.afdhal_studio.foody.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.afdhal_studio.foody.data.database.entities.FavoritesEntity
import com.afdhal_studio.foody.data.database.entities.FoodJokeEntity
import com.afdhal_studio.foody.data.database.entities.RecipesEntity

/**
 *Created by Muh Fuad Afdhal on 02/05/2021
 */

@Database(
    entities = [
        RecipesEntity::class,
        FavoritesEntity::class,
        FoodJokeEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(RecipesTypeConverter::class)
abstract class RecipesDatabase : RoomDatabase() {

    abstract fun recipesDao(): RecipesDao

}