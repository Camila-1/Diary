package com.pchpsky.diary.ui

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.pchpsky.diary.screens.auth.AppAuthViewModel
import com.pchpsky.diary.screens.auth.AuthState
import com.pchpsky.diary.screens.auth.AuthViewModel
import com.pchpsky.diary.screens.auth.ui.SignUp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.junit.Rule
import org.junit.Test

class SignupScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val viewModelDependency = object : AuthViewModel {
        override val uiState: StateFlow<AuthState> = MutableStateFlow(AuthState.ValidationError(
            mapOf("email" to arrayListOf("error message"))))

        override fun createUser(email: String, password: String, passwordConfirmation: String) {}
    }

    @Test
    fun errorIcon_displayedWhenError() {
        composeTestRule.setContent {
            SignUp(viewModelDependency)
        }

        composeTestRule.onNodeWithText("Submit").performClick().assertExists("must not be blank")
        composeTestRule.onNode(hasContentDescription("error")).assertIsDisplayed()
    }

    @Test
    fun submitButton_callCreateUserOnClick() {
        composeTestRule.setContent {
            SignUp(viewModelDependency)
        }

        composeTestRule.onNodeWithText("Submit").performClick()
    }
}

