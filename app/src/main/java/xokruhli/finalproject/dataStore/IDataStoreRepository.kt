package xokruhli.finalproject.dataStore

interface IDataStoreRepository {
    suspend fun saveThemeMode(themeMode: Int)
    suspend fun getThemeMode() : Int
}