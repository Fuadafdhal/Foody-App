package com.afdhal_studio.foody.data

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.afdhal_studio.foody.util.Constants.Companion.DEFAULT_DIET_TYPE
import com.afdhal_studio.foody.util.Constants.Companion.DEFAULT_MEAL_TYPE
import com.afdhal_studio.foody.util.Constants.Companion.PREFERENCES_BACK_ONLINE
import com.afdhal_studio.foody.util.Constants.Companion.PREFERENCES_DIET_TYPE
import com.afdhal_studio.foody.util.Constants.Companion.PREFERENCES_DIET_TYPE_ID
import com.afdhal_studio.foody.util.Constants.Companion.PREFERENCES_MEAL_TYPE
import com.afdhal_studio.foody.util.Constants.Companion.PREFERENCES_MEAL_TYPE_ID
import com.afdhal_studio.foody.util.Constants.Companion.PREFERENCES_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

/**
 *Created by Muh Fuad Afdhal on 02/05/2021
 */

@ActivityRetainedScoped
class DataStoreRepository @Inject constructor(@ApplicationContext private val context: Context) {

    private object PreferenceKeys {
        val selectedMealType = stringPreferencesKey(PREFERENCES_MEAL_TYPE)
        val selectedMealTypeId = intPreferencesKey(PREFERENCES_MEAL_TYPE_ID)
        val selectedDietType = stringPreferencesKey(PREFERENCES_DIET_TYPE)
        val selectedDietTypeId = intPreferencesKey(PREFERENCES_DIET_TYPE_ID)

        val backOnline = booleanPreferencesKey(PREFERENCES_BACK_ONLINE)

    }

//    private var dataStore: DataStore<Preferences> = context.createDataStore(
//        name = PREFERENCES_NAME
//    )

    private val Context.dataStore by preferencesDataStore(
        name = PREFERENCES_NAME
    )

    suspend fun saveMealAndDietType(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    ) {
        context.dataStore.edit {
            it[PreferenceKeys.selectedMealType] = mealType
            it[PreferenceKeys.selectedMealTypeId] = mealTypeId
            it[PreferenceKeys.selectedDietType] = dietType
            it[PreferenceKeys.selectedDietTypeId] = dietTypeId
        }
    }

    suspend fun saveBackOnline(backOnline: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferenceKeys.backOnline] = backOnline
        }
    }

    val readMealAndDietType: Flow<MealAndDietType> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences())
            else throw exception
        }
        .map { preferences ->
            val selectedMealType = preferences[PreferenceKeys.selectedMealType] ?: DEFAULT_MEAL_TYPE
            val selectedMealTypeId = preferences[PreferenceKeys.selectedMealTypeId] ?: 0
            val selectedDietType = preferences[PreferenceKeys.selectedDietType] ?: DEFAULT_DIET_TYPE
            val selectedDietTypeId = preferences[PreferenceKeys.selectedDietTypeId] ?: 0
            MealAndDietType(
                selectedMealType,
                selectedMealTypeId,
                selectedDietType,
                selectedDietTypeId
            )
        }

    val readBackOnline: Flow<Boolean> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences())
            else throw exception
        }
        .map { preferences ->
            val backOnline = preferences[PreferenceKeys.backOnline] ?: false
            backOnline
        }
}

data class MealAndDietType(
    val selectedMealType: String,
    val selectedMealTypeId: Int,
    val selectedDietType: String,
    val selectedDietTypeId: Int
)
