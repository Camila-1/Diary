package com.pchpsky.diary.screens.drawer

import com.pchpsky.diary.R

sealed class NavDrawerItem(var route: String, var icon: Int, var title: String) {
    object Home : NavDrawerItem("home", R.drawable.ic_home, "Home")
    object Settings : NavDrawerItem("settings", R.drawable.ic_settings, "Settings")
}
