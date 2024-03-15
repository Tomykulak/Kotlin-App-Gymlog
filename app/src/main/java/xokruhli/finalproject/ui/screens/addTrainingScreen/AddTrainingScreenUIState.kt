package xokruhli.finalproject.ui.screens.addTrainingScreen

sealed class AddTrainingScreenUIState {
    object Loading: AddTrainingScreenUIState()
    object Default: AddTrainingScreenUIState()
    object TrainingSaved: AddTrainingScreenUIState()
    object TrainingChanged: AddTrainingScreenUIState()
    object TrainingDeleted : AddTrainingScreenUIState()
    data class Error(val message: String) : AddTrainingScreenUIState()
}