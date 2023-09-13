package com.monodev.pams.api.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.stringPreferencesKey
import java.io.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class DataStoreManager(private val context: Context) {
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "config")

    companion object {
        val ISSETUP = intPreferencesKey("isSetup")
    }

    suspend fun saveConfig(issetup: Int) {
        try {
            context.dataStore.edit { preferences ->
                preferences[ISSETUP] = issetup
            }
        } catch (e: IOException) {
            print("例外が発生したよ")
        }
    }

    public fun observeConfig(): Flow<Int?> {
        return context.dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[ISSETUP] ?: 0
            }
    }
}