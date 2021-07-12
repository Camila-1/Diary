package com.pchpsky.diary.ui.auth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pchpsky.diary.navigation.AuthRoute
import com.pchpsky.diary.ui.auth.login.Login
import com.pchpsky.diary.ui.auth.signup.SignUp
import com.pchpsky.diary.ui.auth.start.StartScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : ComponentActivity() {
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
            composable(AuthRoute.LOGIN.route) { Login() }
            composable(AuthRoute.SIGNUP.route) { SignUp() }
        }
    }

}
