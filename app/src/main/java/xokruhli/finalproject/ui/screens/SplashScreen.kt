package xokruhli.finalproject.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import xokruhli.finalproject.BuildConfig
import xokruhli.finalproject.R
import xokruhli.finalproject.navigation.Destination
import xokruhli.finalproject.navigation.INavigationRouter
import xokruhli.finalproject.navigation.NavGraph
import java.util.Timer
import java.util.TimerTask

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplashScreen(
    navigation: INavigationRouter,
) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
            }
            )

        },
    ) {
        SplashScreenContent(paddingValues = it)
    }

    val timer = remember { Timer() }

    LaunchedEffect(Unit) {
        withContext(Dispatchers.Default) {
            delay(400)
        }

        withContext(Dispatchers.Main) {
            navigation.navigateToHomeScreen()
            timer.cancel()
        }
    }
}

@Composable
fun SplashScreenContent(
    paddingValues: PaddingValues
){
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues = paddingValues)){
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Barbell Image",
                modifier = Modifier.fillMaxSize()
            )
    }
}
