package xokruhli.finalproject.ui.screens.trainingDetailScreen

import xokruhli.finalproject.model.TrainingWithExercise

sealed class TrainingDetailScreenUIState {
    object Default : TrainingDetailScreenUIState()
    class Success(val exercises: List<TrainingWithExercise>): TrainingDetailScreenUIState()

}