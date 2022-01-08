package com.pchpsky.diary.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pchpsky.diary.MainNavHost
import com.pchpsky.diary.presentation.theme.DiaryTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainNavHostTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: NavHostController

    @ExperimentalComposeUiApi
    @ExperimentalMaterialApi
    @Before
    fun setupMainNavHost() {
        composeTestRule.setContent {
            navController = rememberNavController()
            DiaryTheme {
                MainNavHost(navController)
            }
        }
    }

    @Test
    fun mainNavHost() {
        composeTestRule
            .onNodeWithContentDescription("Home screen")
            .assertIsDisplayed()
    }

    @Test
    fun openNavigationDrawer() {
        composeTestRule
            .onNodeWithContentDescription("Home screen")
            .performGesture { swipeRight() }

        composeTestRule
            .onNodeWithTag("nav_drawer")
            .assertIsDisplayed()
    }
}