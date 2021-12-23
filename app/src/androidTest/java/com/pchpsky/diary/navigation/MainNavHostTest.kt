package com.pchpsky.diary.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pchpsky.diary.MainNavHost
import com.pchpsky.diary.theme.DiaryTheme
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
    fun setupAuthNavHost() {
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
}