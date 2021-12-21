package com.pchpsky.diary.screens.auth.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import com.pchpsky.diary.MainActivity
import com.pchpsky.diary.theme.DiaryTheme
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class StartScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun clickOnLoginButton_LoginScreenOpens(){
        composeTestRule.setContent {
            DiaryTheme {
                StartScreen(navController = rememberNavController())
            }
        }
        composeTestRule.onNodeWithTag("login_button").performClick()
        composeTestRule.onNodeWithTag("login_screen").assertIsDisplayed()
    }
}