package xokruhli.finalproject.ui.screens.userScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.launch
import xokruhli.finalproject.R
import xokruhli.finalproject.architecture.BaseViewModel
import xokruhli.finalproject.database.ITrainingsRepository
import xokruhli.finalproject.model.User

class UserViewModel(private val repository: ITrainingsRepository): BaseViewModel(), UserActions {
    var id: Long? = null

    var data: UserData = UserData()

    val userUIState: MutableState<UserUIState> = mutableStateOf(UserUIState.Default)

    override fun saveUser() {
        if (data.user.name.isEmpty() || data.user.height == 0 || data.user.weight == 0) {
            //error
            data.error = R.string.EnterAllFields
            userUIState.value = UserUIState.UserChanged
        }
        else if (data.user.weight < 0 || data.user.height < 0) {
            data.error = R.string.greaterThanZero
            userUIState.value = UserUIState.UserChanged
        }
        else {
            launch {
                val id = repository.insertUser(data.user)
                if (id > 0) {
                    userUIState.value = UserUIState.UserSaved
                    data.user.id = id
                }
            }
        }
    }

    override fun updateUser() {
        launch {
            repository.updateUser(data.user)
            userUIState.value = UserUIState.UserSaved
        }
    }

    override fun onNameChanged(name: String) {
        data.user.name = name
        userUIState.value = UserUIState.UserChanged
    }

    override fun onGenderChanged(gender: Boolean) {
        data.user.gender = gender
        userUIState.value = UserUIState.UserChanged
    }

    override fun onWeightChanged(weight: Int) {
        data.user.weight = weight
        userUIState.value = UserUIState.UserChanged
    }

    override fun onHeightChanged(height: Int) {
        data.user.height = height
        userUIState.value = UserUIState.UserChanged
    }

    fun initUser() {
        if (id != null && data.loading) {
            launch {
                data.user = repository.getUserById(id!!)
                data.loading = false
                userUIState.value = UserUIState.UserChanged
            }
        }
        else {
            data.loading = false
            userUIState.value = UserUIState.UserChanged
        }
    }
}