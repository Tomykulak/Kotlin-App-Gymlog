package xokruhli.finalproject.ui.screens.settingScreen

import android.app.Activity
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import xokruhli.finalproject.BuildConfig
import xokruhli.finalproject.R
import xokruhli.finalproject.dataStore.IDataStoreRepository
import xokruhli.finalproject.navigation.INavigationRouter
import xokruhli.finalproject.ui.elements.BottomNavigationBar
import xokruhli.finalproject.ui.elements.CoolButton
import xokruhli.finalproject.ui.theme.Pink40
import xokruhli.finalproject.ui.theme.Pink80


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(
    navigation: INavigationRouter,
    activity: Activity,
    dataStoreRepository: IDataStoreRepository
){
    var selectedThemeIndex by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        selectedThemeIndex = dataStoreRepository.getThemeMode()
    }

    val topAppBarColors = TopAppBarDefaults.topAppBarColors(
        containerColor = Pink80,
        titleContentColor = Pink40,
        navigationIconContentColor = Pink40
    )

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = stringResource(R.string.settings), style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                )
            }, colors = topAppBarColors)
        },
        bottomBar = {
            BottomNavigationBar(navigation = navigation, id = 1)
        }
    ) {
        SettingScreenContent(
            activity = activity,
            dataStoreRepository = dataStoreRepository,
            paddingValues = it
        )
    }
}

@Composable
fun SettingScreenContent(
    activity: Activity,
    dataStoreRepository: IDataStoreRepository,
    paddingValues: PaddingValues
){
    ChangeTheme(activity, dataStoreRepository)
}

@Composable
fun ChangeTheme(
    activity: Activity,
    dataStoreRepository: IDataStoreRepository
) {
    val themeOptions = listOf(
        stringResource(R.string.light),
        stringResource(R.string.dark),
        stringResource(R.string.defaultMode)
    )

    var selectedThemeIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.ChoseVisMode),
            fontSize = 16.sp,
            maxLines = 1,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        Text(stringResource(R.string.version) + BuildConfig.VERSION_NAME)
        ThemeDropdown(
            themeOptions = themeOptions,
            selectedThemeIndex = selectedThemeIndex,
            onThemeSelected = { index ->
                selectedThemeIndex = index
            }
        )

        val coroutineScope = rememberCoroutineScope()

        CoolButton(
            text =   stringResource(R.string.Update),
            onClick = {
                val isDarkModeEnabled = selectedThemeIndex == 1
                setDarkModeEnabled(isDarkModeEnabled, activity)
                Log.d("SelectedThemeIndex", "Value: $selectedThemeIndex")

                val themeMode = when (selectedThemeIndex) {
                    0 -> AppCompatDelegate.MODE_NIGHT_NO
                    1 -> AppCompatDelegate.MODE_NIGHT_YES
                    else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                }
                AppCompatDelegate.setDefaultNightMode(themeMode)

                coroutineScope.launch {
                    withContext(Dispatchers.IO) {
                        dataStoreRepository.saveThemeMode(selectedThemeIndex)
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
fun ThemeDropdown(
    themeOptions: List<String>,
    selectedThemeIndex: Int,
    onThemeSelected: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val dropdownValue = themeOptions[selectedThemeIndex]

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clickable { expanded = true },
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = dropdownValue,
            modifier = Modifier.padding(start = 16.dp)
        )
    }

    if (expanded) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        ) {
            themeOptions.forEachIndexed { index, theme ->
                DropdownMenuItem(
                    text = { Text(theme) },
                    onClick = {
                        onThemeSelected(index)
                        expanded = false
                    }
                )
            }
        }
        LaunchedEffect(selectedThemeIndex) {
            expanded = true
        }
    }
}

fun setDarkModeEnabled(enabled: Boolean, activity: Activity) {
    val uiModeFlags = activity.resources.configuration.uiMode and
            Configuration.UI_MODE_NIGHT_MASK.inv()

    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

    if (enabled) {
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_FULL_USER
        activity.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        activity.window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        activity.window.statusBarColor = Color.Black.toArgb()
        activity.window.navigationBarColor = Color.Black.toArgb()
    } else {
        activity.window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        activity.window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_VISIBLE or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        activity.window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        activity.window.statusBarColor = Color.White.toArgb()
        activity.window.navigationBarColor = Color.White.toArgb()
    }

    when {
        enabled -> uiModeFlags or Configuration.UI_MODE_NIGHT_YES
        else -> uiModeFlags or Configuration.UI_MODE_NIGHT_NO
    }.also { newUiModeFlags ->
        val newConfig = Configuration().apply {
            uiMode = newUiModeFlags
        }
        activity.baseContext.resources.updateConfiguration(
            newConfig,
            activity.baseContext.resources.displayMetrics
        )
    }

    activity.recreate()
}