package xokruhli.finalproject.ui.screens.userScreen

sealed class UserUIState {
    object Default : UserUIState()
    object UserSaved : UserUIState()
    object UserChanged : UserUIState()
}