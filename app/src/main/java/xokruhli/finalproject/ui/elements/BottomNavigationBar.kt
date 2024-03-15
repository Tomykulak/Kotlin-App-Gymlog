package xokruhli.finalproject.ui.elements

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.utils.noRippleClickable
import xokruhli.finalproject.model.BottomNavItem
import xokruhli.finalproject.navigation.Destination
import xokruhli.finalproject.navigation.INavigationRouter
import xokruhli.finalproject.ui.theme.Pink40
import xokruhli.finalproject.ui.theme.Pink80

@Composable
fun BottomNavigationBar(
    navigation: INavigationRouter,
    id: Long
) {

    var selectedIndex: Int

    if (navigation.getNavController().currentDestination!!.route!!.contains(Destination.HomeScreen.route)) {
        selectedIndex = 0
    }
    else if (navigation.getNavController().currentDestination!!.route!!.contains(Destination.UserScreen.route)) {
        selectedIndex = 1
    } else {
        selectedIndex = 2
    }

    Log.d("navigation at", navigation.getNavController().currentDestination!!.route.toString())

    val navigationBarItems = listOf(
        BottomNavItem.HomeScreen,
        BottomNavItem.UserScreen,
        BottomNavItem.SettingScreen
    )

    AnimatedNavigationBar(
        selectedIndex = selectedIndex,
        modifier = Modifier.height(50.dp),
        barColor = Pink80,
        ballColor = Color.Transparent
    ) {
        navigationBarItems.forEach {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .noRippleClickable {
                        when (it) {
                            BottomNavItem.HomeScreen -> {
                                navigation.navigateToHomeScreen()
                            }
                            BottomNavItem.UserScreen -> {
                                navigation.navigateToUserScreen(id = id)
                            }
                            BottomNavItem.SettingScreen -> {
                                navigation.navigateToSettingScreen()
                            }
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(id = it.icon),
                    contentDescription = null,
                    tint = if (selectedIndex == it.index) Color.White else Pink40
                )
            }
        }
    }
}