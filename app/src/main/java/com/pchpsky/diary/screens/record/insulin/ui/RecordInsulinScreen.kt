package com.pchpsky.diary.screens.record.insulin.ui


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.RemoveCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pchpsky.diary.screens.record.FakeRecordInsulinViewModel
import com.pchpsky.diary.screens.record.RecordViewModel
import com.pchpsky.diary.screens.record.insulin.interfacies.RecordInsulinViewModel
import com.pchpsky.diary.theme.DiaryTheme
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState

@ExperimentalComposeUiApi
@Composable
fun InsulinTakingScreen(viewModel: RecordInsulinViewModel = hiltViewModel<RecordViewModel>()) {

    val viewState by viewModel.uiState.collectAsState()
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .background(DiaryTheme.colors.background)
            .fillMaxSize()
            .clickable (
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {focusManager.clearFocus(true) },


        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Points(
            points = viewState.points,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            increment = { viewModel.incrementPoints() },
            decrement = {
                viewModel.decrementPoints()
                        },
            setPoints = { points -> viewModel.setPoints(points) }
        )
    }

}

@ExperimentalComposeUiApi
@Composable
fun Points(
    points: Double,
    modifier: Modifier,
    increment: () -> Unit,
    decrement: () -> Unit,
    setPoints: (Double) -> Unit
) {

    Row(
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        modifier = modifier
            .wrapContentWidth()
            .padding(vertical = 20.dp)
    ) {

        PointsTextField(
            points = points,
            setPoints = { points -> setPoints(points) }
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
fun PointsTextField(points: Double, setPoints: (Double) -> Unit) {

    var value = points
    val keyboardController = LocalSoftwareKeyboardController.current

    BasicTextField(
        value = value.toString(),
        onValueChange = {
            value = it.toDouble()
                        },
        textStyle = DiaryTheme.typography.insulinPoints,
        modifier = Modifier
            .width(IntrinsicSize.Min)
            .height(IntrinsicSize.Min)
            .padding(0.dp)
            .onFocusChanged {
                if (!it.hasFocus) setPoints(value)
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
                setPoints(value)
                keyboardController?.hide()
            }
        )
    )
}

@ExperimentalComposeUiApi
@Composable
@Preview
fun InsulinScreenPreview() {
    DiaryTheme {
        InsulinTakingScreen(FakeRecordInsulinViewModel)
    }
}
