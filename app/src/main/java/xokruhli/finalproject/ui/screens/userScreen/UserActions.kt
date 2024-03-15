package xokruhli.finalproject.ui.screens.userScreen

interface UserActions {
    fun saveUser()
    fun updateUser()
    fun onNameChanged(name: String)
    fun onGenderChanged(gender: Boolean)
    fun onWeightChanged(weight: Int)
    fun onHeightChanged(height: Int)
}