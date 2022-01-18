package com.pchpsky.diary.navigation

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pchpsky.diary.presentation.auth.AuthNavHost
import com.pchpsky.diary.presentation.theme.DiaryTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AuthNavHostTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: NavHostController

    @ExperimentalComposeUiApi
    @Before
    fun setupAuthNavHost() {
        composeTestRule.setContent {
            navController = rememberNavController()
            DiaryTheme {
                AuthNavHost(navController)
            }

        }
    }

    @ExperimentalComposeUiApi
    @Test
    fun authNavHost() {
        composeTestRule
            .onNodeWithContentDescription("Start screen")
            .assertIsDisplayed()
    }

    @Test
    fun authNavHost_NavigateToLoginScreen_viaUI() {
        composeTestRule.onNodeWithContentDescription("Login button").performClick()

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, AuthRoute.LOGIN.route)
    }

    @Test
    fun authNavHost_navigateToSignupScreen_callingNavigate() {
        runBlocking {
            withContext(Dispatchers.Main) {
                navController.navigate(AuthRoute.SIGNUP.route)
            }
        }

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, AuthRoute.SIGNUP.route)
    }
}