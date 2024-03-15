package xokruhli.finalproject.ui.screens.addExerciseScreen

import xokruhli.finalproject.model.Exercise

class AddExerciseData {
    var exercise: Exercise = Exercise(name = "", reps = "0", sets = "0", weight = "0")
    var loading: Boolean = true
    var exerciseTextError: Int? = null
}