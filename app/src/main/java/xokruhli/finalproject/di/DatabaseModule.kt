package xokruhli.finalproject.di

import org.koin.dsl.module
import xokruhli.finalproject.FinalProject
import xokruhli.finalproject.database.TrainingDatabase

val databaseModule = module {
    fun provideDatabase(): TrainingDatabase {
        return TrainingDatabase.getDatabase(FinalProject.appContext)
    }
    single { provideDatabase() }
}