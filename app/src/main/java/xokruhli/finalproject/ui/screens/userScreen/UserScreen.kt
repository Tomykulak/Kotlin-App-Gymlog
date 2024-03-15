package xokruhli.finalproject.ui.screens.userScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.getViewModel
import xokruhli.finalproject.R
import xokruhli.finalproject.navigation.INavigationRouter
import xokruhli.finalproject.ui.elements.BottomNavigationBar
import xokruhli.finalproject.ui.elements.CoolButton
import xokruhli.finalproject.ui.elements.CoolTextField
import xokruhli.finalproject.ui.theme.Pink40
import xokruhli.finalproject.ui.theme.Pink80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserScreen(
    navigation: INavigationRouter,
    id: Long?,
    viewModel: UserViewModel = getViewModel()
) {
    var data: UserData by remember {
        mutableStateOf(viewModel.data)
    }

    viewModel.id = id

    viewModel.userUIState.value.let {
        when(it){
            UserUIState.Default -> {
                LaunchedEffect(it) {
                    viewModel.initUser()
                }
            }
            UserUIState.UserChanged -> {
                data = viewModel.data
                viewModel.userUIState.value = UserUIState.Default

            }
            UserUIState.UserSaved -> {
                LaunchedEffect(it) {
                    navigation.navigateToHomeScreen()
                }
            }
        }
    }
    val topAppBarColors = TopAppBarDefaults.topAppBarColors(
        containerColor = Pink80,
        titleContentColor = Pink40,
        navigationIconContentColor = Pink40
    )

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = stringResource(R.string.yourDetail), style = TextStyle(
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
        UserScreenContent(
            actions = viewModel,
            data = data,
            paddingValues = it
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserScreenContent(
    actions: UserActions,
    data: UserData,
    paddingValues: PaddingValues
){
    if (!data.loading) {
        var expanded by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CoolTextField(
                value = data.user.name,
                onValueChange = { actions.onNameChanged(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null
                    )
                },
                label = stringResource(id = R.string.jmeno)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = {
                        expanded = !expanded
                    }
                ) {
                    OutlinedTextField(
                        value = if (data.user.gender == false) stringResource(R.string.male) else
                            stringResource(R.string.female),
                        label = { Text(text = stringResource(R.string.gender)) },
                        onValueChange = {},
                        readOnly = true,
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text(text = stringResource(R.string.male)) },
                            onClick = {
                                actions.onGenderChanged(false)
                                expanded = false
                            }
                        )
                        DropdownMenuItem(text = { Text(text = stringResource(R.string.female)) },
                            onClick = {
                                actions.onGenderChanged(true)
                                expanded = false
                            }
                        )
                    }
                }
            }

            CoolTextField(
                value = data.user.weight.toString(),
                onValueChange = { actions.onWeightChanged(if ((it.toIntOrNull() != null)) it.toInt() else data.user.weight) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                label = stringResource(R.string.Weight),
                keyboardType = KeyboardType.Number
            )

            CoolTextField(
                value = data.user.height.toString(),
                onValueChange = { actions.onHeightChanged((if ((it.toIntOrNull() != null)) it.toInt() else data.user.height)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                label = stringResource(R.string.Height),
                keyboardType = KeyboardType.Number
            )

            CoolButton(
                text = if(data.user.id == null) stringResource(id = R.string.Save) else stringResource(
                                    R.string.Update),
                onClick = {
                    if (data.error == null) {
                        if (data.user.id == null) {
                            actions.saveUser()
                        } else {
                            actions.updateUser()
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
            )

            if (data.error != null) {
                AlertDialog(
                    onDismissRequest = {
                        data.error = null
                    },
                    title = {
                        Text(text = stringResource(R.string.Alert))
                    },
                    text = {
                        Text(text = stringResource(id = data.error!!))
                    },
                    confirmButton = {
                        Button(

                            onClick = {
                                data.error = null
                            }) {
                            Text("OK")
                        }
                    }
                )
            }
        }
    }
    else {
        CircularProgressIndicator()
    }
}