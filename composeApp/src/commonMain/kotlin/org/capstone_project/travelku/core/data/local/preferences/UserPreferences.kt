package org.capstone_project.travelku.core.data.local.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.capstone_project.travelku.core.data.remote.response.UserData

class UserPreferences(private val dataStore: DataStore<Preferences>) {
    companion object {
        val USER_ID = intPreferencesKey("user_id")
        val EMAIL = stringPreferencesKey("email")
        val USERNAME = stringPreferencesKey("username")
        val TOKEN = stringPreferencesKey("token")
        val ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding_completed")
    }

    suspend fun saveSession(user: UserData) {
        dataStore.edit { preferences ->
            preferences[USER_ID] = user.user_id
            preferences[EMAIL] = user.email
            preferences[USERNAME] = user.username
            preferences[TOKEN] = user.token
        }
    }

    fun getSession(): Flow<UserData> = dataStore.data.map { preferences ->
        UserData(
            user_id = preferences[USER_ID] ?: 0,
            email = preferences[EMAIL] ?: "",
            username = preferences[USERNAME] ?: "",
            token = preferences[TOKEN] ?: ""
        )
    }

    suspend fun saveOnboardingCompleted() {
        dataStore.edit { preferences ->
            preferences[ONBOARDING_COMPLETED] = true
        }
    }

    fun getOnboardingCompleted(): Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[ONBOARDING_COMPLETED] ?: false
    }
}