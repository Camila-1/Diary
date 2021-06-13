package com.pchpsky.diary

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.*
import com.pchpsky.diary.data.Insulin
import com.pchpsky.diary.home.home
import com.pchpsky.diary.navigation.bottomNavigationItems
import com.pchpsky.diary.profile.Sugar
import com.pchpsky.diary.start.login

@Composable
fun Main() {
    val navController = rememberNavController()

    Scaffold(

    ) {
        NavHost(navController = navController, startDestination = "login") {
            composable("login") { login(navController = navController) }
            composable("insulin") { Insulin(navController = navController) }
            composable("sugar") { Sugar(navController = navController) }
        }
    }
}

@Preview
@Composable
fun defaultPreview() {
    home()
}