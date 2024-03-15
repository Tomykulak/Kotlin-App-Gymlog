package xokruhli.finalproject.di

import org.koin.dsl.module
import xokruhli.finalproject.database.TrainingDatabase
import xokruhli.finalproject.database.TrainingsDao

val daoModule = module {
    fun provideDaoModule(database: TrainingDatabase): TrainingsDao = //zmena svoji databaze
        database.trainingsDao() //zmena na svoje dao
    single { provideDaoModule(get()) }
}