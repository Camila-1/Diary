package com.pchpsky.diary.ui

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.pchpsky.diary.screens.auth.FakeViewModel
import com.pchpsky.diary.screens.auth.ui.SignUp
import org.junit.Rule
import org.junit.Test

class SignupScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun errorIcon_displayedWhenError() {
        composeTestRule.setContent {
            SignUp()
        }

        composeTestRule.onNodeWithText("Submit").performClick().assertExists("must not be blank")
        composeTestRule.onNode(hasContentDescription("error")).assertIsDisplayed()
    }

    @Test
    fun submitButton_callCreateUserOnClick() {
        composeTestRule.setContent {
            SignUp()
        }

        composeTestRule.onNodeWithText("Submit").performClick()
    }
}

