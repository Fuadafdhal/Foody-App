package com.afdhal_studio.foody.data

import com.afdhal_studio.foody.data.database.RecipesDao
import com.afdhal_studio.foody.data.database.RecipesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 *Created by Muh Fuad Afdhal on 02/05/2021
 */

class LocalDataSource @Inject constructor(
    private val recipesDao: RecipesDao
) {
    fun readDatabase(): Flow<List<RecipesEntity>> {
        return recipesDao.readRecipes()
    }

    suspend fun insertRecipes(recipesEntity: RecipesEntity) {
        recipesDao.insertRecipes(recipesEntity)
    }
}