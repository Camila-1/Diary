package com.pchpsky.diary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pchpsky.diary.composables.TopBar
import com.pchpsky.diary.composables.drawer.Drawer
import com.pchpsky.diary.navigation.MainRout
import com.pchpsky.diary.screens.home.Home
import com.pchpsky.diary.screens.settings.SettingsViewModel
import com.pchpsky.diary.screens.settings.ui.InsulinSettings
import com.pchpsky.diary.screens.settings.ui.Settings
import com.pchpsky.diary.theme.DiaryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DiaryTheme(darkTheme = false) {
                Surface {
                    val scaffoldState =
                        rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Closed))
                    val scope = rememberCoroutineScope()

                    val navController = rememberNavController()

                    Scaffold(
                        scaffoldState = scaffoldState,
                        topBar = { TopBar(scope = scope, scaffoldState = scaffoldState) },
                        content = { MainNavHost(navController) },
                        drawerContent = {
                            Drawer(
                                scope = scope,
                                scaffoldState = scaffoldState,
                                navController = navController
                            )
                        }
                    )
                }
            }
        }
    }

    @ExperimentalComposeUiApi
    @Composable
    fun MainNavHost(navController: NavHostController) {
        NavHost(navController, MainRout.HOME.route) {
            composable(MainRout.HOME.route) { Home(navController) }
            composable(MainRout.SETTINGS.route) {
                Settings(
                    navController,
                    viewModel<SettingsViewModel>()
                )
            }
            composable(MainRout.INSULIN_SETTINGS.route) { InsulinSettings(viewModel<SettingsViewModel>()) }
        }
    }
}
