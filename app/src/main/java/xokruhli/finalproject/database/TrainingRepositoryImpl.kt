package xokruhli.finalproject.database

import kotlinx.coroutines.flow.Flow
import xokruhli.finalproject.model.Exercise
import xokruhli.finalproject.model.Training
import xokruhli.finalproject.model.TrainingWithExercise
import xokruhli.finalproject.model.User

class TrainingRepositoryImpl(private val trainingsDao: TrainingsDao): ITrainingsRepository {
    // trainings
    override fun getAll(): Flow<List<Training>> {
        return trainingsDao.getAll()
    }

    override suspend fun insertTraining(training: Training): Long {
        return trainingsDao.insertTraining(training = training)
    }

    override suspend fun updateTraining(training: Training) {
        trainingsDao.updateTraining(training = training)
    }

    override suspend fun getTrainingById(trainingId: Long): Training {
        return trainingsDao.getTrainingById(trainingId =  trainingId)
    }

    override suspend fun deleteTraining(training: Training) {
        return trainingsDao.deleteTraining(training = training)
    }
    // exercises
    override suspend fun insertExercise(exercise: Exercise): Long {
        return trainingsDao.insertExercise(exercise = exercise)
    }

    override suspend fun updateExercise(exercise: Exercise) {
        trainingsDao.updateExercise(exercise = exercise)
    }

    override suspend fun getExerciseById(exerciseId: Long): Exercise {
        return trainingsDao.getExerciseById(exerciseId = exerciseId)
    }

    override suspend fun deleteExercise(exerciseId: Long) {
        trainingsDao.deleteExercise(exerciseId = exerciseId)
    }

    // training and exercise related
    override suspend fun deleteExercisesByTrainingId(trainingId: Long) {
        return trainingsDao.deleteExercisesByTrainingId(trainingId = trainingId)
    }

    override fun getTrainingWithExercises(trainingId: Long): Flow<List<TrainingWithExercise>> {
        return trainingsDao.getTrainingWithExercises(trainingId = trainingId)
    }

    // user
    override suspend fun insertUser(user: User): Long {
        return trainingsDao.insertUser(user)
    }

    override suspend fun updateUser(user: User) {
        trainingsDao.updateUser(user)
    }

    override suspend fun getUserById(id: Long): User {
        return trainingsDao.getUserById(id)
    }

    override suspend fun countUser(): Int {
        return trainingsDao.countUser()
    }


}