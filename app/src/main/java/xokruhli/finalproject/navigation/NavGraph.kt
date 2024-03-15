package xokruhli.finalproject.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import xokruhli.finalproject.dataStore.DataStoreRepositoryImpl
import xokruhli.finalproject.dataStore.IDataStoreRepository
import xokruhli.finalproject.ui.screens.SplashScreen
import xokruhli.finalproject.ui.screens.addExerciseScreen.AddExerciseScreen
import xokruhli.finalproject.ui.screens.addTrainingScreen.AddTrainingScreen
import xokruhli.finalproject.ui.screens.homeScreen.HomeScreen
import xokruhli.finalproject.ui.screens.settingScreen.SettingScreen
import xokruhli.finalproject.ui.screens.trainingDetailScreen.TrainingDetailScreen
import xokruhli.finalproject.ui.screens.userScreen.UserScreen

@Composable
fun NavGraph(
    activity: Activity,
    dataStoreRepository: IDataStoreRepository = DataStoreRepositoryImpl(activity),
    navController: NavHostController = rememberNavController(),
    navigation: INavigationRouter = remember {
        NavigationRouterImpl(navController)
    },
    startDestination: String
){
    NavHost(navController = navController,
        startDestination = startDestination){

        composable(Destination.HomeScreen.route) {
            HomeScreen(navigation = navigation)
        }

        composable(Destination.SplashScreen.route){
            SplashScreen(navigation = navigation)
        }

        composable(Destination.AddTrainingScreen.route + "/{trainingId}",
            arguments = listOf(
                navArgument("trainingId"){
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )
        ){
            val id = it.arguments?.getLong("trainingId")
            AddTrainingScreen(
                navigation = navigation,
                id = if (id != -1L) id else null
            )
        }

        composable(Destination.TrainingDetailScreen.route + "/{trainingId}",
            arguments = listOf(
                navArgument("trainingId"){
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )
        ){
            val id = it.arguments?.getLong("trainingId")
            TrainingDetailScreen(
                navigation = navigation,
                id = if (id != -1L) id else null
            )
        }

        composable(Destination.AddExerciseScreen.route + "/{trainingId}/{exerciseId}",
            arguments = listOf(
                navArgument("trainingId"){
                    type = NavType.LongType
                    defaultValue = -1L
                },
                navArgument("exerciseId"){
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )
        ){
            val trainingId = it.arguments?.getLong("trainingId")
            val exerciseId = it.arguments?.getLong("exerciseId")
            AddExerciseScreen(
                navigation = navigation,
                trainingId = if (trainingId != -1L) trainingId else null,
                exerciseId = if (exerciseId != -1L) exerciseId else null
            )
        }
        composable(Destination.UserScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )
        ) {
            val id = it.arguments!!.getLong("id")

            UserScreen(navigation = navigation, id = if(id == -1L) null else id)
        }

        composable(Destination.SettingScreen.route){
            SettingScreen(
                activity = activity,
                dataStoreRepository = dataStoreRepository,
                navigation = navigation
            )
        }
    }
}