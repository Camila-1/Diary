package com.pchpsky.diary.components.drawer

import com.pchpsky.diary.R
import com.pchpsky.diary.navigation.MainRout

sealed class NavDrawerItem(var route: String, var icon: Int, var title: String) {
    object Home : NavDrawerItem(MainRout.HOME.route, R.drawable.ic_home, "Home")
    object Settings : NavDrawerItem(MainRout.SETTINGS.route, R.drawable.ic_settings, "Settings")
    object RecordInsulin : NavDrawerItem(MainRout.INSULIN.route, R.drawable.ic_insulin, "Record Insulin")
    object RecordGlucose : NavDrawerItem(MainRout.RECORD_GLUCOSE.route, R.drawable.ic_sugar, "Record Glucose")
}
