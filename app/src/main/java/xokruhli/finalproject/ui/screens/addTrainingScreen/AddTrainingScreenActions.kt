package xokruhli.finalproject.ui.screens.addTrainingScreen

import android.net.Uri

interface AddTrainingScreenActions {
    fun saveTraining()
    fun deleteTraining()
    fun onTextChange(text: String)

    fun onImgPathChanged(uri: Uri)
}