package com.pchpsky.diary.screens.auth

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.pchpsky.diary.navigation.AuthRoute
import com.pchpsky.diary.screens.auth.ui.Login
import com.pchpsky.diary.screens.auth.ui.SignUp
import com.pchpsky.diary.screens.auth.ui.StartScreen
import com.pchpsky.diary.theme.DiaryTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AuthActivity : ComponentActivity() {

    private val viewModel: AppAuthViewModel by viewModels()

    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.attributes.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }


        setContent {
            DiaryTheme(darkTheme = true) {
                ProvideWindowInsets {
                    AuthNavHost(rememberNavController())
                }
            }
        }
    }

    @ExperimentalComposeUiApi
    @Composable
    fun AuthNavHost(navController: NavHostController) {
        NavHost(navController, AuthRoute.START.route) {
            composable(AuthRoute.START.route) { StartScreen(navController) }
            composable(AuthRoute.LOGIN.route) { Login(viewModel) }
            composable(AuthRoute.SIGNUP.route) { SignUp(viewModel) }
        }
    }
}
