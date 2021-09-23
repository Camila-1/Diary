package com.pchpsky.diary

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.pchpsky.diary.composables.TopBar
import com.pchpsky.diary.composables.drawer.Drawer
import com.pchpsky.diary.navigation.MainRout
import com.pchpsky.diary.screens.home.Home
import com.pchpsky.diary.screens.home.HomeViewModel
import com.pchpsky.diary.screens.settings.ui.Settings
import com.pchpsky.diary.theme.DiaryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: HomeViewModel by viewModels()

    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DiaryTheme(darkTheme = false) {
                Surface {
                    val scaffoldState =
                        rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Closed))
                    val scope = rememberCoroutineScope()

                    val systemUiController = rememberSystemUiController()

//                    SideEffect {
//                        systemUiController.setStatusBarColor(DiaryTheme.colors.primary)
//                    }

                    Scaffold(
                        scaffoldState = scaffoldState,
                        topBar = { TopBar(scope = scope, scaffoldState = scaffoldState) },
                        content = { MainNavHost(rememberNavController()) },
                        drawerContent = {
                            Drawer(
                                scope = scope,
                                scaffoldState = scaffoldState,
                                navController = rememberNavController()
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
            composable(MainRout.SETTINGS.route) { Settings() }
        }
    }
}
