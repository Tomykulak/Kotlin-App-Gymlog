package xokruhli.finalproject.ui.screens.addExerciseScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.getViewModel
import xokruhli.finalproject.R
import xokruhli.finalproject.navigation.INavigationRouter
import xokruhli.finalproject.ui.elements.BackArrowScreen
import xokruhli.finalproject.ui.elements.CoolButton
import xokruhli.finalproject.ui.elements.MyTextField
import xokruhli.finalproject.ui.theme.Pink40
import xokruhli.finalproject.ui.theme.Pink80

@Composable
fun AddExerciseScreen(
    navigation: INavigationRouter,
    viewModel: AddExerciseViewModel = getViewModel(),
    trainingId: Long? = null,
    exerciseId: Long? = null
){
    viewModel.trainingId = trainingId
    viewModel.exerciseId = exerciseId


    var data:AddExerciseData by remember {
        mutableStateOf(viewModel.data)
    }

    viewModel.addExerciseUIState.value.let {
        when(it){
            AddExerciseUIState.Default -> {

            }
            AddExerciseUIState.ExerciseChanged -> {
                data = viewModel.data
                viewModel.addExerciseUIState.value =
                    AddExerciseUIState.Default
            }
            AddExerciseUIState.ExerciseSaved -> {
                LaunchedEffect(it){
                    navigation.navigateBack()
                    //navigation.navigateBack()
                }
            }
            AddExerciseUIState.Loading -> {
                viewModel.initExercise()
            }
            is AddExerciseUIState.Error -> {

            }
            AddExerciseUIState.ExerciseDeleted -> {
                LaunchedEffect(it){
                    navigation.navigateBack()
                }
            }
        }
    }

    BackArrowScreen(
        appBarTitle = stringResource(R.string.editExercise),
        actions = {
            if(exerciseId != null){
                IconButton(onClick = { viewModel.deleteExercise() }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null)
                }
            }
        },
        onBackClick = {
            navigation.navigateBack()
        }) {
        AddExerciseScreenContent(
            actions = viewModel,
            navigation = navigation,
            data = data,
            trainingId = trainingId!!
        )
    }
}

@Composable
fun AddExerciseScreenContent(
    actions: AddExerciseActions,
    navigation: INavigationRouter,
    data: AddExerciseData,
    trainingId: Long
){
    if(!data.loading){
        val maxChar: Int = 15
        Column(
            modifier = Modifier
                .wrapContentSize()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.Start) {
            MyTextField(
                value = data.exercise.name,
                hint = stringResource(R.string.exerciseName),
                onValueChange = {
                    if(data.exercise.name.length <= maxChar) actions.onTextChange(it)
                },
                error =
                if (data.exerciseTextError != null){
                    stringResource(id = data.exerciseTextError!!)
                } else {
                    ""
                }
            )

            MyTextField(
                value = data.exercise.sets,
                hint = stringResource(R.string.Sets),
                onValueChange = {
                    if(data.exercise.sets.length <= maxChar) actions.onSetsChange(it)
                },
                error =
                if (data.exerciseTextError != null){
                    stringResource(id = data.exerciseTextError!!)
                } else {
                    ""
                }
            )

            MyTextField(
                value = data.exercise.reps,
                hint = stringResource(R.string.reps),
                onValueChange = {
                    if(data.exercise.reps.length <= maxChar) actions.onRepsChange(it)
                },
                error =
                if (data.exerciseTextError != null){
                    stringResource(id = data.exerciseTextError!!)
                } else {
                    ""
                }
            )

            MyTextField(
                value = data.exercise.weight,
                hint = stringResource(R.string.WeightS),
                onValueChange = {
                    if(data.exercise.weight.length <= maxChar) actions.onWeightChange(it)
                },
                error =
                if (data.exerciseTextError != null){
                    stringResource(id = data.exerciseTextError!!)
                } else {
                    ""
                }
            )

            CoolButton(
                text = stringResource(R.string.saveExer),
                onClick = {
                    actions.saveExercise(trainingId)
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

    } else {
        CircularProgressIndicator()
    }
}