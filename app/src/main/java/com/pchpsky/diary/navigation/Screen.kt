package com.pchpsky.diary.navigation

import androidx.annotation.StringRes
import com.pchpsky.diary.R


sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: Int) {
    object Insulin : Screen("insulin", R.string.insulin, R.drawable.ic_insulin)
    object Sugar : Screen("sugar", R.string.sugar, R.drawable.ic_sugar)
}

val bottomNavigationItems = listOf(
    Screen.Insulin,
    Screen.Sugar,
)