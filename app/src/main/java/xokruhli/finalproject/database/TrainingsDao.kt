package xokruhli.finalproject.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import xokruhli.finalproject.model.Exercise
import xokruhli.finalproject.model.Training
import xokruhli.finalproject.model.TrainingWithExercise
import xokruhli.finalproject.model.User

@Dao
interface TrainingsDao {
    // trainings
    @Query("SELECT * FROM trainings")
    fun getAll(): Flow<List<Training>>

    @Insert
    suspend fun insertTraining(training: Training): Long

    @Update
    suspend fun updateTraining(training: Training)

    @Query("SELECT * FROM trainings WHERE trainingId = :trainingId")
    suspend fun getTrainingById(trainingId: Long): Training

    @Delete
    suspend fun deleteTraining(training: Training)
    // exercises
    @Insert
    suspend fun insertExercise(exercise: Exercise): Long

    @Update
    suspend fun updateExercise(exercise: Exercise)

    @Query("SELECT * FROM exercises WHERE exerciseId = :exerciseId")
    suspend fun getExerciseById(exerciseId: Long): Exercise
    // training and exercise related
    @Transaction
    @Query("SELECT * FROM trainings WHERE trainingId=:trainingId")
    fun getTrainingWithExercises(trainingId: Long): Flow<List<TrainingWithExercise>>

    @Query("DELETE FROM exercises WHERE trainingId = :trainingId")
    suspend fun deleteExercisesByTrainingId(trainingId: Long)

    @Query("DELETE FROM exercises WHERE exerciseId = :exerciseId")
    suspend fun deleteExercise(exerciseId: Long)

    // user

    @Insert
    suspend fun insertUser(user: User): Long

    @Update
    suspend fun updateUser(user: User)

    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getUserById(id: Long): User

    @Query ("SELECT COUNT(id) AS count FROM user")
    suspend fun countUser(): Int
}