package xokruhli.finalproject.navigation

sealed class Destination(val route: String) {
    object SplashScreen: Destination(route = "splash_screen")
    object HomeScreen : Destination(route = "home_screen")

    object AddTrainingScreen : Destination(route = "add_training_screen")

    object AddExerciseScreen: Destination(route = "add_exercise")

    object TrainingDetailScreen: Destination(route = "training_detail")

    object UserScreen: Destination(route = "user_screen")

    object SettingScreen: Destination(route = "setting_screen")

}