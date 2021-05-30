package com.pchpsky.diary

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.*
import com.pchpsky.diary.data.Insulin
import com.pchpsky.diary.navigation.bottomNavigationItems
import com.pchpsky.diary.profile.Sugar
import com.pchpsky.diary.start.Login
import com.pchpsky.diary.statistics.Statistics

@Composable
fun Main() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.arguments?.getString("key")
                bottomNavigationItems.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(painterResource(screen.icon), "") },
                        label = { Text(stringResource(id = screen.resourceId)) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpToId.plus(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        }
    ) {
        NavHost(navController = navController, startDestination = "login") {
            composable("login") { Login(navController = navController) }
            composable("insulin") { Insulin(navController = navController) }
            composable("sugar") { Sugar(navController = navController) }
        }
    }
}

//@Preview
//@Composable
//fun DefaultPreview() {
//    Main()
//}