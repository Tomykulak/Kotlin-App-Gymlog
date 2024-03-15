package xokruhli.finalproject.ui.screens.userScreen
import xokruhli.finalproject.model.User

class UserData {
    var user: User = User("", false, 0, 0)
    var error: Int? = null
    var loading: Boolean = true
}