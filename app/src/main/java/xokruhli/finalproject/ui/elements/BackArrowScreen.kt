package xokruhli.finalproject.ui.elements

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import xokruhli.finalproject.ui.theme.Pink40
import xokruhli.finalproject.ui.theme.Pink80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackArrowScreen(
    appBarTitle: String,
    onBackClick: () -> Unit,
    drawFullScreenContent: Boolean = false,
    actions: @Composable RowScope.() -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    content: @Composable (paddingValues: PaddingValues) -> Unit
) {
    val topAppBarColors = TopAppBarDefaults.topAppBarColors(
        containerColor = Pink80,
        titleContentColor = Pink40,
        navigationIconContentColor = Pink40
    )

    Scaffold(
        topBar = {
            TopAppBar(
                actions = actions,
                title = {
                    Text(text = appBarTitle, style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        onBackClick()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription ="")
                    }

                },
                colors = topAppBarColors
            )
        },
        floatingActionButton = floatingActionButton
    ) {
        if (!drawFullScreenContent) {
            LazyColumn(modifier = Modifier.padding(it)) {
                item {
                    content(it)
                }
            }
        } else {
            content(it)
        }
    }
}