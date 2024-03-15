package xokruhli.finalproject.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import xokruhli.finalproject.ui.screens.addExerciseScreen.AddExerciseViewModel
import xokruhli.finalproject.ui.screens.addTrainingScreen.AddTrainingScreenViewModel
import xokruhli.finalproject.ui.screens.homeScreen.HomeScreenViewModel
import xokruhli.finalproject.ui.screens.trainingDetailScreen.TrainingDetailScreenViewModel
import xokruhli.finalproject.ui.screens.userScreen.UserViewModel

val viewModelModule = module {

    viewModel {
        HomeScreenViewModel(get())
    }
    viewModel {
        AddTrainingScreenViewModel(get())
    }

    viewModel{
        TrainingDetailScreenViewModel(get())
    }

    viewModel {
        AddExerciseViewModel(get())
    }
    viewModel {
        UserViewModel(get())
    }

}