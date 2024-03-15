package xokruhli.finalproject.ui.screens.homeScreen

import xokruhli.finalproject.model.Training

sealed class HomeScreenUIState {
    object Default : HomeScreenUIState()
    class Success(val trainings: List<Training>) : HomeScreenUIState()
}
