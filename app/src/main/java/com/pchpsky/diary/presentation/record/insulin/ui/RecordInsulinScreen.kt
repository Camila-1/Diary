package com.pchpsky.diary.presentation.record.insulin.ui


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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pchpsky.diary.data.network.model.Insulin
import com.pchpsky.diary.presentation.components.*
import com.pchpsky.diary.presentation.record.FakeRecordInsulinViewModel
import com.pchpsky.diary.presentation.record.RecordViewModel
import com.pchpsky.diary.presentation.record.insulin.interfacies.RecordInsulinViewModel
import com.pchpsky.diary.presentation.theme.DiaryTheme
import com.pchpsky.diary.presentation.theme.blue
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@SuppressLint("CoroutineCreationDuringComposition")
@ExperimentalComposeUiApi
@Composable
fun RecordInsulinScreen(
    viewModel: RecordInsulinViewModel = hiltViewModel<RecordViewModel>(),
    onBackClick: () -> Unit
) {

    val viewState by viewModel.uiState.collectAsState()
    val focusManager = LocalFocusManager.current
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(true) {
        viewModel.insulins()
    }

    @Composable
    fun Screen() {
        if (viewState.loading) return
        Column(
            modifier = Modifier
                .background(DiaryTheme.colors.background)
                .fillMaxSize()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { focusManager.clearFocus(true) },


            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
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

            InsulinDropDownMenu(
                selectedInsulin = viewState.selectedInsulin,
                insulins = viewState.insulins,
                show = viewState.dropDownInsulinMenu,
                onClick = { viewModel.dropInsulinMenu(true) },
                select = { viewModel.selectInsulin(it) },
                dismiss = { viewModel.dropInsulinMenu(false) }
            )

            Text(
                text = viewState.time,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .clickable {
                        viewModel.showTimePicker(true)
                    },
                color = DiaryTheme.colors.primary
            )
        }

    }

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
                onBackClick()
            }
        }
    ) {
        ProgressBar(viewState.loading)
        Screen()

        if (viewState.unitsInputError.isNotEmpty()) {
            scope.launch {
                scaffoldState.snackbarHostState.showSnackbar(viewState.unitsInputError)
            }
        }

        TimePicker(
            show = viewState.showTimePicker,
            close = { viewModel.showTimePicker(false) },
            selectTime = {
                viewModel.setTime(it)
            }
        )
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

        UnitsInputField(
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

@ExperimentalMaterialApi
@Composable
fun InsulinDropDownMenu(
    selectedInsulin: Insulin?,
    insulins: List<Insulin>,
    show: Boolean,
    onClick: () -> Unit,
    select: (Insulin) -> Unit,
    dismiss: () -> Unit
) {

    ExposedDropdownMenuBox(
        expanded = show,
        onExpandedChange = { dismiss() },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(5.dp)
            .background(Color.Transparent),
    ) {

        if (selectedInsulin != null) {
            Row(
                modifier = Modifier
                    .clickable { onClick() }
            ) {
                InsulinColorCircle(
                    color = Color(android.graphics.Color.parseColor(selectedInsulin.color)),
                    modifier = Modifier
                        .align(Alignment.Bottom)
                )

                Text(
                    text = selectedInsulin.name,
                    style = DiaryTheme.typography.body,
                    color = Color.White,
                    modifier = Modifier.padding(start = 15.dp)
                )
            }
        }

        ExposedDropdownMenu(
            expanded = show,
            onDismissRequest = { dismiss() },
            modifier = Modifier
                .background(Color.Transparent)

        ) {
            insulins.forEach {
                DropdownMenuItem(
                    onClick = {
                        select(it)
                        dismiss()
                    },
                    modifier = Modifier
                        .background(DiaryTheme.colors.background)
                ) {
                    InsulinMenuItem(insulin = it)
                }
            }
        }
    }
}

@Composable
fun InsulinMenuItem(insulin: Insulin) {

    Row {
        InsulinColorCircle(
            color = Color(android.graphics.Color.parseColor(insulin.color)),
            modifier = Modifier
                .align(Alignment.Bottom)
        )

        Text(
            text = insulin.name,
            style = DiaryTheme.typography.body,
            color = Color.White,
            modifier = Modifier.padding(start = 15.dp)
        )
    }
}

//@Composable
//fun TimePicker(show: Boolean, close: (LocalTime) -> Unit) {
//
//    val timePickerDialogState = rememberMaterialDialogState()
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(DiaryTheme.colors.background)
//    ) {
//
//        MaterialDialog(
//            dialogState = timePickerDialogState,
//            buttons = {
//                positiveButton("Ok")
//                negativeButton("Cancel")
//            }
//        ) {
//            if (show) timepicker(
//                title = "Select Time",
//                colors = TimePickerDefaults.colors(
//                    activeBackgroundColor = DiaryTheme.colors.primary
//                )
//            ) {
//                close(it)
//            }
//        }
//    }
//}

@ExperimentalComposeUiApi
@Composable
fun UnitsInputField(units: MutableState<String>, setUnits: (String) -> Unit) {

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

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
@Preview
fun InsulinScreenPreview() {
    DiaryTheme {
        RecordInsulinScreen(FakeRecordInsulinViewModel) {}
    }
}
