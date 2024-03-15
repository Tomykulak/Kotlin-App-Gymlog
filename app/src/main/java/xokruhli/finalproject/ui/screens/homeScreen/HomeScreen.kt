package xokruhli.finalproject.ui.screens.homeScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import org.koin.androidx.compose.getViewModel
import xokruhli.finalproject.FinalProject
import xokruhli.finalproject.R
import xokruhli.finalproject.model.Training
import xokruhli.finalproject.navigation.INavigationRouter
import xokruhli.finalproject.ui.elements.BottomNavigationBar
import xokruhli.finalproject.ui.theme.Pink40
import xokruhli.finalproject.ui.theme.Pink70
import xokruhli.finalproject.ui.theme.Pink80
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigation: INavigationRouter,
    viewModel: HomeScreenViewModel = getViewModel()
){
    var trainings = remember {
        mutableStateListOf<Training>()
    }
    // screen states
    viewModel.homeScreenUIState.value.let {
        when(it){
            HomeScreenUIState.Default -> {
                viewModel.loadTrainings()
                viewModel.initUser()
            }
            is HomeScreenUIState.Success -> {
                trainings.clear()
                trainings.addAll(it.trainings)
            }
        }
    }
    val topAppBarColors = TopAppBarDefaults.topAppBarColors(
        containerColor = Pink80,
        titleContentColor = Pink40,
        navigationIconContentColor = Color.White
    )
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = stringResource(R.string.trainings), style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                ))
            },
            colors = topAppBarColors
            )
        },
        bottomBar = {
            BottomNavigationBar(navigation = navigation, id = 1)
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navigation.navigateToAddTrainingScreen(-1L)
            },
            containerColor = Pink80
            )
            {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }
    ) {
        HomeScreenContent(
            paddingValues = it,
            navigation = navigation,
            trainings = trainings
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    paddingValues: PaddingValues,
    navigation: INavigationRouter,
    trainings: MutableList<Training>
) {
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues = paddingValues)
            .fillMaxSize()
    ) {
        if(trainings.isNotEmpty()){
            trainings.forEach { training ->
                item(key = training.trainingId) {
                    Surface(
                        shape = RoundedCornerShape(20),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                            .height(70.dp),
                        color = Pink70,
                        onClick = {
                            navigation.navigateToTrainingDetailScreen(training.trainingId)
                        }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(0.10f)
                                    .size(96.dp)  // Increased image size to 96.dp
                                    .clip(CircleShape)
                            ) {
                                if (training.image.isNotEmpty()) {
                                    val cacheFile = File(FinalProject.appContext.filesDir, training.image)
                                    AsyncImage(
                                        model = cacheFile,
                                        contentDescription = null,
                                        modifier = Modifier.fillMaxSize()
                                    )
                                } else {
                                    Icon(
                                        painter = painterResource(id = R.drawable.addbox),
                                        contentDescription = null,
                                        modifier = Modifier.height(80.dp).align(alignment = Alignment.Center)
                                    )
                                }
                            }
                            Text(
                                text = training.name,
                                modifier = Modifier.weight(1f).padding(start = 16.dp, end = 16.dp),
                                fontSize = 20.sp
                            )
                            IconButton(
                                onClick = {
                                    navigation.navigateToAddTrainingScreen(training.trainingId)
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = stringResource(R.string.Edit),
                                    tint = Color.Gray
                                )
                            }
                        }
                    }
                }
            }
        } else {
            item{
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 20.dp,
                        vertical = 30.dp)
                ){
                    Text(stringResource(R.string.AddFirstTraining), style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ))
                }
            }
        }
    }
}
