package xokruhli.finalproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import xokruhli.finalproject.model.Exercise
import xokruhli.finalproject.model.Training
import xokruhli.finalproject.model.TrainingWithExercise
import xokruhli.finalproject.model.User

@Database(entities = [Training::class, Exercise::class, User::class], version = 18, exportSchema = true) //item na muj model
abstract class TrainingDatabase : RoomDatabase() {

    abstract fun trainingsDao(): TrainingsDao //zmenit na moje dao

    companion object {
        private var INSTANCE: TrainingDatabase? = null
        fun getDatabase(context: Context): TrainingDatabase {
            if (INSTANCE == null) {
                synchronized(TrainingDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            TrainingDatabase::class.java, "trainings_database"
                        )/*destrukce nici starou verzi databaze(DROP TABLE), tudiz mizi i data*/
                            .fallbackToDestructiveMigration().build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}