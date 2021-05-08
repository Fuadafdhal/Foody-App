package com.afdhal_studio.foody.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.afdhal_studio.foody.models.Result
import com.afdhal_studio.foody.util.Constants.Companion.FAVORITE_RECIPES_TABLE

@Entity(tableName = FAVORITE_RECIPES_TABLE)
data class FavoritesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var result: Result
)
