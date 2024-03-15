package xokruhli.finalproject.ui.screens.trainingDetailScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.getViewModel
import xokruhli.finalproject.R
import xokruhli.finalproject.model.Exercise
import xokruhli.finalproject.model.TrainingWithExercise
import xokruhli.finalproject.navigation.INavigationRouter
import xokruhli.finalproject.ui.elements.BackArrowScreen
import xokruhli.finalproject.ui.elements.ExerciseRow
import xokruhli.finalproject.ui.screens.addTrainingScreen.AddTrainingScreenContent
import xokruhli.finalproject.ui.screens.addTrainingScreen.AddTrainingScreenData
import xokruhli.finalproject.ui.screens.addTrainingScreen.AddTrainingScreenViewModel
import xokruhli.finalproject.ui.theme.Pink80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainingDetailScreen(
    navigation: INavigationRouter,
    viewModel: TrainingDetailScreenViewModel = getViewModel(),
    id: Long?
){
    viewModel.trainingId = id

    var exercises = remember { mutableStateListOf<TrainingWithExercise>()}

    viewModel.trainingDetailScreenUIState.value.let {
        when(it){
            TrainingDetailScreenUIState.Default -> {
                viewModel.loadExercises(viewModel.trainingId!!)
            }
            is TrainingDetailScreenUIState.Success -> {
                exercises.clear()
                exercises.addAll(it.exercises)
            }
        }
    }

    BackArrowScreen(
        appBarTitle = (stringResource(R.string.Exercises)),
        actions = {
        },
        onBackClick = {
            navigation.navigateBack()
        },
        floatingActionButton ={
            FloatingActionButton(
                onClick = {
                    navigation.navigateToAddExerciseScreen(exerciseId = -1L, trainingId = id)
                },containerColor = Pink80
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }
        ) {
        TrainingDetailScreenContent(
            paddingValues = it,
            exercises = exercises,
            navigation = navigation,
            actions = viewModel,
            id = id
        )
    }
}

@Composable
fun TrainingDetailScreenContent(
    paddingValues: PaddingValues,
    exercises: MutableList<TrainingWithExercise>,
    navigation: INavigationRouter,
    actions: TrainingDetailScreenActions,
    id: Long?
) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.Start)
    {
        if(exercises.isNotEmpty()){
            exercises.forEach { training ->
                training.exercises.forEach { exercise ->
                    ExerciseRow(
                        exercise = exercise,
                        onRowClick = {
                            navigation.navigateToAddExerciseScreen(exercise.exerciseId, id)
                        }
                    )
                }
            }
        } else {
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 20.dp,
                    vertical = 30.dp)
            ){
                Text(stringResource(R.string.AddFirstExercise), style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                )
            }
        }

    }
}