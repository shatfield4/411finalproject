package edu.fullerton.fz.finalproject411


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStoreFile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class FavoritesDataStore private constructor(private val dataStore: DataStore<Preferences>) {

    fun isFavorite(cryptoName: String): Flow<Boolean> {
        val key = booleanPreferencesKey(cryptoName)
        return this.dataStore.data
            .map { prefs -> prefs[key] ?: false }
            .distinctUntilChanged()
    }

    suspend fun setFavorite(cryptoName: String, isFavorite: Boolean) {
        val key = booleanPreferencesKey(cryptoName)
        this.dataStore.edit { prefs -> prefs[key] = isFavorite }
    }

    companion object {
        private const val PREFERENCES_DATA_FILE_NAME = "favorites"
        private var INSTANCE: FavoritesDataStore? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                val dataStore = PreferenceDataStoreFactory.create {
                    context.preferencesDataStoreFile(PREFERENCES_DATA_FILE_NAME)
                }
                INSTANCE = FavoritesDataStore(dataStore)
            }
        }

        fun getStore(): FavoritesDataStore {
            return INSTANCE ?: throw IllegalStateException("FavoritesDataStore not initialized yet")
        }
    }
}
