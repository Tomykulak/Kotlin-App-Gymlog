package xokruhli.finalproject.navigation

import androidx.navigation.NavController

interface INavigationRouter {
    fun navigateBack()
    fun navigateToHomeScreen()
    fun navigateToSplashScreen()
    fun navigateToAddTrainingScreen(id: Long?)
    fun navigateToTrainingDetailScreen(id: Long?)
    fun navigateToAddExerciseScreen(trainingId: Long?, exerciseId: Long?)

    fun navigateToUserScreen(id: Long?)

    fun navigateToSettingScreen()
    fun getNavController(): NavController

}