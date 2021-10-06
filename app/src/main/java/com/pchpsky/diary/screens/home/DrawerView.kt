package com.pchpsky.diary.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.pchpsky.diary.theme.DiaryTheme

@Composable
fun DrawerView(navController: NavController) {
    Column {
        TextButton(
            onClick = {

            },
            modifier = Modifier,
            enabled = true,
            shape = DiaryTheme.shapes.navigationDrawer,
            content = { Text(text = "Settings") }
        )
    }
}