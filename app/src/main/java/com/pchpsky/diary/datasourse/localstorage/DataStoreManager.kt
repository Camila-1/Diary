package com.pchpsky.diary.datasourse.localstorage

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

object PreferencesKeys {
    val USER_TOKEN = stringPreferencesKey("user_token")
}

class DataStoreManager @Inject constructor(@ApplicationContext appContext: Context) {

    private val Context.dataStore by preferencesDataStore("user_preferences")

    private val dataStore = appContext.dataStore

    suspend fun saveUserToken(token: String) {
        dataStore.edit {
            it[PreferencesKeys.USER_TOKEN] = token
        }
    }

    fun userToken(): Flow<String?> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) emit(emptyPreferences())
            else throw exception
        }.map { it[PreferencesKeys.USER_TOKEN] }
    }
}