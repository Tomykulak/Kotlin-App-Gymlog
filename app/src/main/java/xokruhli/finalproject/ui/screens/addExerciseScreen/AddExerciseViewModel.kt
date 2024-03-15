package xokruhli.finalproject.ui.screens.addExerciseScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import xokruhli.finalproject.R
import xokruhli.finalproject.architecture.BaseViewModel
import xokruhli.finalproject.database.ITrainingsRepository
import xokruhli.finalproject.ui.screens.addTrainingScreen.AddTrainingScreenUIState

class AddExerciseViewModel(
    private val repository: ITrainingsRepository
    ): BaseViewModel(), AddExerciseActions {

    var data: AddExerciseData = AddExerciseData()
    var exerciseId: Long? = null
    var trainingId: Long? = null


    var addExerciseUIState: MutableState<AddExerciseUIState> =
        mutableStateOf(AddExerciseUIState.Loading)

    override fun saveExercise(trainingId: Long) {
        if (data.exercise.name.isEmpty()) {
            data.exerciseTextError = R.string.cannot_be_empty
            addExerciseUIState.value = AddExerciseUIState.ExerciseChanged
        } else {
            launch {
                val exercise = data.exercise
                if (exerciseId == null) {
                    exercise.trainingId = trainingId
                    val id = repository.insertExercise(exercise)
                    if (id > 0) {
                        addExerciseUIState.value = AddExerciseUIState.ExerciseSaved
                    } else {
                        // Handle error here
                    }
                } else {
                    exercise.exerciseId = exerciseId
                    exercise.trainingId = trainingId
                    repository.updateExercise(exercise)
                    addExerciseUIState.value = AddExerciseUIState.ExerciseSaved
                }
            }
        }
    }


    override fun onTextChange(text: String) {
        data.exercise.name = text
        addExerciseUIState.value = AddExerciseUIState.ExerciseChanged
    }

    override fun onRepsChange(reps: String) {
        data.exercise.reps = reps
        addExerciseUIState.value = AddExerciseUIState.ExerciseChanged
    }

    override fun onWeightChange(weight: String) {
        data.exercise.weight = weight
        addExerciseUIState.value = AddExerciseUIState.ExerciseChanged
    }

    override fun onSetsChange(sets: String) {
        data.exercise.sets = sets
        addExerciseUIState.value = AddExerciseUIState.ExerciseChanged
    }


    override fun deleteExercise() {
        viewModelScope.launch {
            repository.deleteExercise(trainingId!!)
            addExerciseUIState.value = AddExerciseUIState.ExerciseDeleted
        }
    }

    fun initExercise(){
        if (exerciseId != null) {
            launch {
                data.exercise = repository.getExerciseById(trainingId!!)
                data.loading = false
                addExerciseUIState.value = AddExerciseUIState.ExerciseChanged
            }
        } else {
            data.loading = false
            addExerciseUIState.value = AddExerciseUIState.ExerciseChanged
        }
    }

}