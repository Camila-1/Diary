package com.pchpsky.diary.presentation.components

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import com.pchpsky.diary.presentation.components.drawer.Drawer
import com.pchpsky.diary.presentation.theme.DiaryTheme
import org.junit.Rule
import org.junit.Test

class TopBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeTopBarWhenMenuButtonClick_NavigationDrawerOpens() {
        composeTestRule.setContent {
            val coroutineScope = rememberCoroutineScope()
            val scaffoldState = rememberScaffoldState()

            DiaryTheme {
                Scaffold(
                    topBar = { HomeTopBar(coroutineScope, scaffoldState) },
                    drawerContent = {
                        Drawer(
                            scope = coroutineScope,
                            scaffoldState = scaffoldState,
                            navController = rememberNavController()
                        )
                    }
                ) {}
            }
        }

        composeTestRule.onNodeWithContentDescription("nav_drawer_menu_icon").performClick()
        composeTestRule.onNodeWithTag("nav_drawer").assertIsDisplayed()
    }
}