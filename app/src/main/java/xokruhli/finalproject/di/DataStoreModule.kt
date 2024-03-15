package xokruhli.finalproject.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import xokruhli.finalproject.dataStore.DataStoreRepositoryImpl
import xokruhli.finalproject.dataStore.IDataStoreRepository

val dataStoreModule = module {
    single { provideDataStoreRepository(androidContext()) }
}

fun provideDataStoreRepository(context: Context): IDataStoreRepository
        = DataStoreRepositoryImpl(context)