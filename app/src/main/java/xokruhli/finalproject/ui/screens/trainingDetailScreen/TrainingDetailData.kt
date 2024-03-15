package xokruhli.finalproject.ui.screens.trainingDetailScreen

import xokruhli.finalproject.model.Exercise

class TrainingDetailData {
    var exercise: Exercise = Exercise(name = "", reps = "0", sets = "0", weight = "0")
    var loading: Boolean = true
    var exerciseTextError: Int? = null
}