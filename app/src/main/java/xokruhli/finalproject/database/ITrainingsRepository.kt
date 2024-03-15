package xokruhli.finalproject.database

import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import xokruhli.finalproject.model.Exercise
import xokruhli.finalproject.model.Training
import xokruhli.finalproject.model.TrainingWithExercise
import xokruhli.finalproject.model.User

interface ITrainingsRepository {

    // trainings
    fun getAll(): Flow<List<Training>>
    suspend fun insertTraining(training: Training): Long
    suspend fun updateTraining(training: Training)
    suspend fun getTrainingById(trainingId: Long): Training
    suspend fun deleteTraining(training: Training)

    // exercises
    suspend fun insertExercise(exercise: Exercise): Long
    suspend fun updateExercise(exercise: Exercise)
    suspend fun getExerciseById(exerciseId: Long): Exercise

    suspend fun deleteExercise(exerciseId: Long)

    // training and exercise related
    fun getTrainingWithExercises(trainingId: Long): Flow<List<TrainingWithExercise>>
    suspend fun deleteExercisesByTrainingId(trainingId: Long)

    // user
    suspend fun insertUser(user: User): Long
    suspend fun updateUser(user: User)
    suspend fun getUserById(id: Long): User
    suspend fun countUser(): Int
}