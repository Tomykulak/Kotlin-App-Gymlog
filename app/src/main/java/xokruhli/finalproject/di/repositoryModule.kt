package xokruhli.finalproject.di

import org.koin.dsl.module
import xokruhli.finalproject.database.ITrainingsRepository
import xokruhli.finalproject.database.TrainingRepositoryImpl
import xokruhli.finalproject.database.TrainingsDao

val repositoryModule = module{
    fun provideItemsRepository(dao: TrainingsDao): ITrainingsRepository { //zmena
        return TrainingRepositoryImpl(dao) //zmena
    }
    single { provideItemsRepository(get()) }
}