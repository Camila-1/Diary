package com.pchpsky.diary.presentation.settings.ui

import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.pchpsky.diary.presentation.theme.DiaryTheme
import org.junit.Rule
import org.junit.Test

class SettingsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun glucoseUnitMilligramsPerDecilitre_RadioButtonSwitchesToMilligramsPerDecilitreValue() {
        composeTestRule.setContent {
            DiaryTheme {
                Glucose(unit = "mg/dL", onClick = {})
            }
        }

        composeTestRule.onNodeWithTag("mg/dL").assertIsSelected()
    }
}