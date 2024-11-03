package org.capstone_project.travelku.ui.presentation.screen.onboarding

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OnBoardingUtils(prefs: DataStore<Preferences>) {
    private val onBoardingCompletedKey = booleanPreferencesKey("onBoardingCompleted")

    private val dataStore = prefs

    val onBoardingCompleted: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[onBoardingCompletedKey] ?: false
    }

    suspend fun setOnBoardingCompleted(onBoardingCompleted: Boolean) {
        dataStore.edit { preferences ->
            preferences[onBoardingCompletedKey] = onBoardingCompleted
        }
    }
}