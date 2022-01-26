package com.pchpsky.diary.presentation.auth

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.pchpsky.diary.navigation.AuthRoute
import com.pchpsky.diary.presentation.auth.login.LoginScreen
import com.pchpsky.diary.presentation.auth.signup.SignUpScreen
import com.pchpsky.diary.presentation.auth.start.StartScreen
import com.pchpsky.diary.presentation.theme.DiaryTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AuthActivity : ComponentActivity() {

    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.attributes.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }

        setContent {
            DiaryTheme(darkTheme = true) {
                ProvideWindowInsets {
                    AuthNavHost(rememberNavController())
                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun AuthNavHost(navController: NavHostController) {
    NavHost(navController, AuthRoute.START.route) {
        composable(AuthRoute.START.route) {
            StartScreen(
                navigateToLoginScreen = { navController.navigate(AuthRoute.LOGIN.route) },
                navigateToSignupScreen = { navController.navigate(AuthRoute.SIGNUP.route) }
            )
        }
        composable(AuthRoute.LOGIN.route) { LoginScreen() }
        composable(AuthRoute.SIGNUP.route) { SignUpScreen() }
    }
}
