package xokruhli.finalproject.model

import xokruhli.finalproject.R

sealed class BottomNavItem(
    val index: Int,
    val icon: Int,
) {
    object HomeScreen : BottomNavItem(0, R.drawable.home)
    object UserScreen: BottomNavItem(1, R.drawable.person)

    object SettingScreen: BottomNavItem(2, R.drawable.settings)
}