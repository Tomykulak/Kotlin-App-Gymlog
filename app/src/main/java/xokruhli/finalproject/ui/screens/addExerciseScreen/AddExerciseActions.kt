package xokruhli.finalproject.ui.screens.addExerciseScreen

interface AddExerciseActions {
    fun saveExercise(trainingId: Long)
    fun onTextChange(text: String)

    fun onRepsChange(reps: String)

    fun onWeightChange(weight: String)

    fun onSetsChange(sets: String)

    fun deleteExercise()
}