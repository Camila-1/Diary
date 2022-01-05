package com.pchpsky.diary.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.pchpsky.diary.R
import com.pchpsky.diary.theme.DiaryTheme
import com.pchpsky.diary.theme.blue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeTopBar(scope: CoroutineScope, scaffoldState: ScaffoldState) {
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name), fontSize = 18.sp) },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(Icons.Filled.Menu, "nav_drawer_menu_icon")
            }
        },
        backgroundColor = DiaryTheme.colors.primary,
        contentColor = Color.White
    )
}

@Composable
fun RecordInsulinTopBar(onBackClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = "Record Insulin", fontSize = 18.sp) },
        navigationIcon = {
            IconButton(onClick = {
                onBackClick()
            }) {
                Icon(Icons.Filled.ArrowBack, "")
            }
        },
        backgroundColor = blue,
        contentColor = Color.White
    )
}

@Composable
fun RecordGlucoseTopBar(onBackClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = "Record Glucose", fontSize = 18.sp) },
        navigationIcon = {
            IconButton(onClick = {
                onBackClick()
            }) {
                Icon(Icons.Filled.ArrowBack, "")
            }
        },
        backgroundColor = DiaryTheme.colors.recordGlucoseTopBar,
        contentColor = Color.White
    )
}

@Composable
fun SettingsTopBar(onBackClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = "Settings", fontSize = 18.sp) },
        navigationIcon = {
            IconButton(onClick = {
                onBackClick()
            }) {
                Icon(Icons.Filled.ArrowBack, "")
            }
        },
        backgroundColor = DiaryTheme.colors.settingsTopBar,
        contentColor = Color.White
    )
}

@Preview(showBackground = false)
@Composable
fun HomeTopBarPreview() {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    DiaryTheme {
        HomeTopBar(scope = scope, scaffoldState = scaffoldState)
    }
}

@Preview(showBackground = false)
@Composable
fun RecordInsulinTopBarPreview() {
    DiaryTheme {
        RecordInsulinTopBar {}
    }
}

@Preview(showBackground = false)
@Composable
fun SettingsTopBarPreview() {
    DiaryTheme {
        SettingsTopBar {}
    }
}

@Preview(showBackground = false)
@Composable
fun RecordGlucoseTopBarPreview() {
    DiaryTheme {
        RecordGlucoseTopBar {}
    }
}