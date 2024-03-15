package xokruhli.finalproject.ui.screens.addExerciseScreen

import xokruhli.finalproject.ui.screens.addTrainingScreen.AddTrainingScreenUIState

sealed class AddExerciseUIState {
    object Loading: AddExerciseUIState()
    object Default: AddExerciseUIState()
    object ExerciseSaved: AddExerciseUIState()
    object ExerciseChanged: AddExerciseUIState()

    object ExerciseDeleted: AddExerciseUIState()

    data class Error(val message: String) : AddExerciseUIState()
}