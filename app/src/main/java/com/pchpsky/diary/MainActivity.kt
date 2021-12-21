package com.pchpsky.diary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pchpsky.diary.components.HomeTopBar
import com.pchpsky.diary.components.drawer.Drawer
import com.pchpsky.diary.navigation.MainRout
import com.pchpsky.diary.screens.home.Home
import com.pchpsky.diary.screens.record.glucose.ui.RecordGlucoseScreen
import com.pchpsky.diary.screens.record.insulin.ui.RecordInsulinScreen
import com.pchpsky.diary.screens.settings.SettingsViewModel
import com.pchpsky.diary.screens.settings.ui.Settings
import com.pchpsky.diary.theme.DiaryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @ExperimentalMaterialApi
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DiaryTheme {
                val navController = rememberNavController()
                MainNavHost(navController)
            }
        }
    }

    @ExperimentalMaterialApi
    @ExperimentalComposeUiApi
    @Composable
    fun MainNavHost(navController: NavHostController) {

        val settingsViewModel: SettingsViewModel by viewModels()

        NavHost(navController, MainRout.HOME.route) {
            composable(MainRout.HOME.route) {
                val scaffoldState =
                    rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Closed))
                val scope = rememberCoroutineScope()

                Scaffold(
                    topBar = { HomeTopBar(scope, scaffoldState) },
                    drawerContent = {
                        Drawer(
                            scope = scope,
                            scaffoldState = scaffoldState,
                            navController = navController
                        )
                    }
                ) {
                    Home()
                }

            }
            composable(MainRout.SETTINGS.route) {
                Settings(settingsViewModel) {
                    navController.popBackStack()
                }
            }
            composable(MainRout.INSULIN.route) {
                RecordInsulinScreen {
                    navController.popBackStack()
                }
            }
            composable(MainRout.RECORD_GLUCOSE.route) {
                RecordGlucoseScreen {
                    navController.popBackStack()
                }
            }
        }
    }
}
