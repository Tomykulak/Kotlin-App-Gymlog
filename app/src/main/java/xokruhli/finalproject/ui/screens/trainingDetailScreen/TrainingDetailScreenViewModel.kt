package xokruhli.finalproject.ui.screens.trainingDetailScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.launch
import xokruhli.finalproject.architecture.BaseViewModel
import xokruhli.finalproject.database.ITrainingsRepository
import xokruhli.finalproject.ui.screens.addTrainingScreen.AddTrainingScreenData

class TrainingDetailScreenViewModel(
    private val repository: ITrainingsRepository
): BaseViewModel(), TrainingDetailScreenActions {

    var trainingDetailScreenUIState: MutableState<TrainingDetailScreenUIState> =
        mutableStateOf(TrainingDetailScreenUIState.Default)

    var data: AddTrainingScreenData = AddTrainingScreenData()
    var trainingId: Long? = null

    fun loadExercises(trainingId: Long){
        launch {
            repository.getTrainingWithExercises(trainingId = trainingId).collect {
                 trainingDetailScreenUIState.value = TrainingDetailScreenUIState.Success(it)
             }
        }
    }
}