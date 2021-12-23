package com.pchpsky.diary.components

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import com.pchpsky.diary.theme.DiaryTheme
import org.junit.Rule
import org.junit.Test

class OutlinedTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenValidationError_errorMessageIsDisplayed() {

        composeTestRule.setContent {
            val textFieldValue = remember { mutableStateOf("") }

            DiaryTheme {
                OutlinedTextField(
                    value = textFieldValue,
                    label = "Test",
                    errorMessage = "error message",
                    keyboardType = KeyboardType.Ascii,
                    visualTransformation = VisualTransformation.None
                )
            }
        }

        composeTestRule.onNodeWithTag("error_message_text").assertIsDisplayed().assertTextContains("error message")
    }

    @Test
    fun whenValidationError_errorIconIsDisplayed() {

        composeTestRule.setContent {
            val textFieldValue = remember { mutableStateOf("") }

            DiaryTheme {
                OutlinedTextField(
                    value = textFieldValue,
                    label = "Test",
                    errorMessage = "error message",
                    keyboardType = KeyboardType.Ascii,
                    visualTransformation = VisualTransformation.None
                )
            }
        }

        composeTestRule.onNodeWithContentDescription("error_icon").assertIsDisplayed()
    }
}