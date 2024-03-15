package xokruhli.finalproject.ui.screens.addTrainingScreen

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatDelegate
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.compose.getViewModel
import xokruhli.finalproject.FinalProject
import xokruhli.finalproject.R
import xokruhli.finalproject.navigation.INavigationRouter
import xokruhli.finalproject.ui.elements.BackArrowScreen
import xokruhli.finalproject.ui.elements.CoolButton
import xokruhli.finalproject.ui.elements.MyTextField
import xokruhli.finalproject.ui.screens.settingScreen.setDarkModeEnabled
import java.io.File

@Composable
fun AddTrainingScreen(
    navigation: INavigationRouter,
    viewModel: AddTrainingScreenViewModel = getViewModel(),
    id: Long?,
){
    viewModel.trainingId = id

    var data: AddTrainingScreenData by remember {
        mutableStateOf(viewModel.data)
    }

    viewModel.addTrainingScreenUIState.value.let {
        when(it){
            AddTrainingScreenUIState.Default -> {

            }
            AddTrainingScreenUIState.Loading -> {
                viewModel.initTraining()
            }
            AddTrainingScreenUIState.TrainingChanged -> {
                data = viewModel.data
                viewModel.addTrainingScreenUIState.value =
                    AddTrainingScreenUIState.Default
            }
            AddTrainingScreenUIState.TrainingSaved -> {
                LaunchedEffect(it){
                    navigation.navigateBack()
                }
            }

            is AddTrainingScreenUIState.Error -> {

            }
            AddTrainingScreenUIState.TrainingDeleted -> {
                LaunchedEffect(it){
                    navigation.navigateBack()
                }
            }
        }
    }
    BackArrowScreen(
        appBarTitle = stringResource(R.string.editTraining),
        actions = {
            if(id != null){
                IconButton(onClick = { viewModel.deleteTraining() }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null)
                }
            }
        },
        onBackClick = {
            navigation.navigateBack()
        }) {
        AddTrainingScreenContent(
            actions = viewModel,
            navigation = navigation,
            data = data
        )
    }
}

@Composable
fun AddTrainingScreenContent(
    actions: AddTrainingScreenActions,
    navigation: INavigationRouter,
    data: AddTrainingScreenData
){
    // image
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            if (it != null) {
                actions.onImgPathChanged(uri = it)
            }
        }
    )
    if (!data.loading) {
        Row(){
            MyTextField(
                value = data.training.name,
                hint = stringResource(R.string.trainingName),
                onValueChange = {
                    actions.onTextChange(it)
                },
                error = if (data.trainingTextError != null)
                    stringResource(id = data.trainingTextError!!) else "")
        }
        // image picker
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            CoolButton(
                text = stringResource(R.string.chooseImage),
                onClick = {
                    singlePhotoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        // chosen image
        Row() {
            if (data.training.image.isNotEmpty()) {
                val cacheFile = File(FinalProject.appContext.filesDir, data.training.image)
                AsyncImage(
                    model = cacheFile, contentDescription = null, modifier = Modifier.fillMaxWidth())
            }
        }
        // save button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            CoolButton(
                text = stringResource(R.string.saveTraining),
                onClick = {
                    actions.saveTraining()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.Bottom)
            )
        }
    } else {
        Text(stringResource(R.string.loading))
        Text(data.loading.toString())
        CircularProgressIndicator()
    }
}