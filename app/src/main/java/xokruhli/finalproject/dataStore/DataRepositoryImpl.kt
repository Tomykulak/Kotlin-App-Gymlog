package xokruhli.finalproject.dataStore

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.first

class DataStoreRepositoryImpl(private val context: Context) : IDataStoreRepository {
    val dataStoreKey = intPreferencesKey("theme_mode")

    override suspend fun saveThemeMode(themeMode: Int) {
        context.dataStore.edit { preferences ->
            preferences[dataStoreKey] = themeMode
        }
    }
    override suspend fun getThemeMode(): Int {
        val preferences = context.dataStore.data.first()
        return preferences[dataStoreKey] ?: AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
    }
}