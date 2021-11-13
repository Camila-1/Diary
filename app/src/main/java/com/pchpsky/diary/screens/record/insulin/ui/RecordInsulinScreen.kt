package com.pchpsky.diary.screens.record.insulin.ui


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.RemoveCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pchpsky.diary.components.DiarySnackbar
import com.pchpsky.diary.components.RecordInsulinTopBar
import com.pchpsky.diary.screens.record.FakeRecordInsulinViewModel
import com.pchpsky.diary.screens.record.RecordViewModel
import com.pchpsky.diary.screens.record.insulin.interfacies.RecordInsulinViewModel
import com.pchpsky.diary.theme.DiaryTheme
import com.pchpsky.diary.theme.blue
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@ExperimentalComposeUiApi
@Composable
fun RecordInsulinScreen(
    navController: NavController,
    viewModel: RecordInsulinViewModel = hiltViewModel<RecordViewModel>()
) {

    val viewState by viewModel.uiState.collectAsState()
    val focusManager = LocalFocusManager.current
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(
                hostState = it,
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 10.dp),
                snackbar = {
                    DiarySnackbar(it, blue, Modifier.padding(vertical = 10.dp, horizontal = 15.dp))
                }
            )
        },
        topBar = {
            RecordInsulinTopBar {
                navController.popBackStack()
            }
        }
    ) {
        Column(
            modifier = Modifier
                .background(DiaryTheme.colors.background)
                .fillMaxSize()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { focusManager.clearFocus(true) },


            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Units(
                units = viewState.units.toString(),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                increment = { viewModel.incrementUnits() },
                decrement = {
                    viewModel.decrementUnits()
                },
                setUnits = { units -> viewModel.setUnits(units) }
            )
        }

        if (viewState.unitsInputError.isNotEmpty()) {
            scope.launch {
                scaffoldState.snackbarHostState.showSnackbar(viewState.unitsInputError)
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@ExperimentalComposeUiApi
@Composable
fun Units(
    units: String,
    modifier: Modifier,
    increment: () -> Unit,
    decrement: () -> Unit,
    setUnits: (String) -> Unit
) {

    Row(
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        modifier = modifier
            .wrapContentWidth()
            .padding(vertical = 20.dp)
    ) {

        PointsTextField(
            units = mutableStateOf(units),
            setUnits = { points -> setUnits(points.toString()) }
        )

        Column(
            modifier = Modifier.align(Alignment.CenterVertically),
        ) {
            IconButton(onClick = { increment() }) {
                Icon(
                    imageVector = Icons.Rounded.AddCircle,
                    contentDescription = "",
                    modifier = Modifier.size(36.dp),
                    tint = Color.White
                )
            }

            IconButton(onClick = { decrement() }) {
                Icon(
                    imageVector = Icons.Rounded.RemoveCircle,
                    contentDescription = null,
                    modifier = Modifier.size(36.dp),
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun TimePicker(show: Boolean) {
    if (!show) return

    val timePickerDialogState = rememberMaterialDialogState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DiaryTheme.colors.background)
    ) {

        MaterialDialog(
            dialogState = timePickerDialogState,
            buttons = {
                positiveButton("Ok")
                negativeButton("Cancel")
            }
        ) {
            timepicker { time ->
            }
        }

        timePickerDialogState.show()
    }
}

@ExperimentalComposeUiApi
@Composable
fun PointsTextField(units: MutableState<String>, setUnits: (String) -> Unit) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    BasicTextField(
        value = units.value,
        onValueChange = {
            units.value = it
        },
        textStyle = DiaryTheme.typography.insulinUnits,
        modifier = Modifier
            .width(250.dp)
            .height(IntrinsicSize.Min)
            .padding(horizontal = 10.dp)
            .onFocusChanged {
                if (!it.isCaptured) {
                    setUnits(units.value)
                }
            },
        decorationBox = {
            it()
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Number
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                setUnits(units.value)
                keyboardController?.hide()
                focusManager.clearFocus()
            }
        ),
        cursorBrush = Brush.verticalGradient(
            0.00f to Color.Transparent,
            0.27f to Color.Transparent,
            0.27f to Color.White,
            0.80f to Color.White,
            0.80f to Color.Transparent,
            1.00f to Color.Transparent
        )
    )
}

@ExperimentalComposeUiApi
@Composable
@Preview
fun InsulinScreenPreview() {
    DiaryTheme {
        RecordInsulinScreen(rememberNavController(), FakeRecordInsulinViewModel)
    }
}
