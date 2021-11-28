package com.pchpsky.diary.screens.record.glucose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pchpsky.diary.components.RecordGlucoseTopBar
import com.pchpsky.diary.theme.DiaryTheme

@Composable
fun RecordGlucoseScreen(navController: NavController) {

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { RecordGlucoseTopBar { navController.popBackStack() } }
    ) {
        Box(
            modifier = Modifier.fillMaxSize().background(DiaryTheme.colors.background)
        )
    }
}

@Composable
@Preview
fun RecordGlucosePreview() {
    DiaryTheme {
        RecordGlucoseScreen(rememberNavController())
    }
}