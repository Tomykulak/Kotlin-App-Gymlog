package xokruhli.finalproject

import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import xokruhli.finalproject.dataStore.dataStore
import xokruhli.finalproject.di.*

class FinalProject: Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        startKoin {
            androidContext(this@FinalProject) //zmenit
            modules(
                listOf(
                    //TODO moduly //prepisu modily v DI slozce
                    viewModelModule,
                    repositoryModule,
                    daoModule,
                    databaseModule,
                    dataStoreModule
                )
            )
        }
    }
    companion object {
        lateinit var appContext: Context
            private set
    }
}