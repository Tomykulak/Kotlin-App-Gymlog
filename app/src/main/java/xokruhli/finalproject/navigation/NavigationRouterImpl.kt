package xokruhli.finalproject.navigation

import androidx.navigation.NavController

class NavigationRouterImpl(
    private val navController: NavController
    ): INavigationRouter {
    override fun navigateBack() {
        navController.popBackStack()
    }

    override fun navigateToHomeScreen() {
        navController.navigate(Destination.HomeScreen.route)
    }

    override fun navigateToSplashScreen() {
        navController.navigate(Destination.SplashScreen.route)
    }

    override fun navigateToAddTrainingScreen(id: Long?) {
        navController.navigate(Destination.AddTrainingScreen.route + "/" + id)
    }

    override fun navigateToTrainingDetailScreen(id: Long?) {
        navController.navigate(Destination.TrainingDetailScreen.route + "/" + id)
    }

    override fun navigateToAddExerciseScreen(trainingId: Long?, exerciseId: Long?) {
        navController.navigate(Destination.AddExerciseScreen.route + "/" + trainingId + "/" + exerciseId)
    }

    override fun navigateToUserScreen(id: Long?) {
        navController.navigate(Destination.UserScreen.route + "/" + id)
    }

    override fun navigateToSettingScreen() {
        navController.navigate(Destination.SettingScreen.route)
    }

    override fun getNavController(): NavController = navController
}