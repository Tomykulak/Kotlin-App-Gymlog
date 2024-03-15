package xokruhli.finalproject.ui.screens.homeScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import xokruhli.finalproject.architecture.BaseViewModel
import xokruhli.finalproject.database.ITrainingsRepository
import xokruhli.finalproject.model.User
import xokruhli.finalproject.ui.screens.userScreen.UserUIState

class HomeScreenViewModel(
    private val repository: ITrainingsRepository
    ) : BaseViewModel(), HomeScreenActions {

    val homeScreenUIState: MutableState<HomeScreenUIState> =
        mutableStateOf(HomeScreenUIState.Default)

    fun loadTrainings() {
        launch {
            repository.getAll().collect{
                homeScreenUIState.value = HomeScreenUIState.Success(it)
            }
        }
    }
    fun initUser(){

        launch {
            if(repository.countUser() < 1){
                repository.insertUser(User("", false, 0, 0))
            }
        }
    }
}
