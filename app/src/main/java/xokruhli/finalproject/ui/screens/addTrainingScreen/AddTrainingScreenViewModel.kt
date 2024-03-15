package xokruhli.finalproject.ui.screens.addTrainingScreen

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.net.toUri
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import xokruhli.finalproject.FinalProject
import xokruhli.finalproject.R
import xokruhli.finalproject.architecture.BaseViewModel
import xokruhli.finalproject.database.ITrainingsRepository

class AddTrainingScreenViewModel(
    private val repository: ITrainingsRepository
    ) : BaseViewModel(), AddTrainingScreenActions
{
    var data: AddTrainingScreenData = AddTrainingScreenData()
    var trainingId: Long? = null

    val addTrainingScreenUIState: MutableState<AddTrainingScreenUIState> = mutableStateOf(AddTrainingScreenUIState.Loading)
    override fun saveTraining() {
        if(data.training.name.isEmpty()) {
            data.trainingTextError = R.string.cannot_be_empty
            addTrainingScreenUIState.value = AddTrainingScreenUIState.TrainingChanged
        } else {
            launch {
                if (trainingId == null) {
                    val id = repository.insertTraining(data.training)
                    if (id > 0) {
                        addTrainingScreenUIState.value = AddTrainingScreenUIState.TrainingSaved
                    }
                } else {
                    repository.updateTraining(data.training)
                    addTrainingScreenUIState.value = AddTrainingScreenUIState.TrainingSaved
                }
            }
        }
    }

    override fun deleteTraining() {
        viewModelScope.launch {
            trainingId?.let { id ->
                val training = repository.getTrainingById(id)
                if (training != null) {
                    repository.deleteExercisesByTrainingId(id)
                    repository.deleteTraining(training)
                    addTrainingScreenUIState.value = AddTrainingScreenUIState.TrainingDeleted
                } else {
                    // Handle the case where the training doesn't exist or is already deleted
                    addTrainingScreenUIState.value = AddTrainingScreenUIState.Error("Training not found")
                }
            }
        }
    }


    override fun onTextChange(text: String) {
        data.training.name = text
        addTrainingScreenUIState.value = AddTrainingScreenUIState.TrainingChanged
    }

    override fun onImgPathChanged(uri: Uri) {
        val input = FinalProject.appContext.contentResolver.openInputStream(uri)
        val outputFile = FinalProject.appContext.filesDir.resolve(uri.lastPathSegment.toString())
        if (input != null) {
            input.copyTo(outputFile.outputStream())
            data.training.image = outputFile.toUri().lastPathSegment.toString()
        }

        addTrainingScreenUIState.value = AddTrainingScreenUIState.TrainingChanged
    }

    fun initTraining() {
        if (trainingId != null){
            launch {
                data.training = repository.getTrainingById(trainingId = trainingId!!)
                data.loading = false
                addTrainingScreenUIState.value = AddTrainingScreenUIState.TrainingChanged
            }
        } else {
            data.loading = false
            addTrainingScreenUIState.value = AddTrainingScreenUIState.TrainingChanged
        }
    }
}