package com.pchpsky.diary.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.pchpsky.diary.theme.DiaryTheme
import org.junit.Rule
import org.junit.Test

class ProgressBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun passTrue_ProgressBarIsDisplayed() {
        composeTestRule.setContent {
            DiaryTheme {
                ProgressBar(isDisplayed = true)
            }
        }
        composeTestRule.onNodeWithTag("progress_bar").assertIsDisplayed()
    }
}