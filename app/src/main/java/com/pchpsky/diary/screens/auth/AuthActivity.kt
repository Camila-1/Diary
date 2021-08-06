package com.pchpsky.diary.screens.auth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pchpsky.diary.navigation.AuthRoute
import com.pchpsky.diary.screens.auth.ui.Login
import com.pchpsky.diary.screens.auth.ui.SignUp
import com.pchpsky.diary.screens.auth.ui.StartScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : ComponentActivity() {

    private val viewModel: AppAuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold {
                AuthNavHost(rememberNavController())
            }

        }
    }

    @Composable
    fun AuthNavHost(navController: NavHostController) {
        NavHost(navController, AuthRoute.START.route) {
            composable(AuthRoute.START.route) { StartScreen(navController) }
            composable(AuthRoute.LOGIN.route) { Login(viewModel) }
            composable(AuthRoute.SIGNUP.route) { SignUp(viewModel) }
        }
    }

}
