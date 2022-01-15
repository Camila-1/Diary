package com.pchpsky.diary.presentation.record.insulin.ui


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.imePadding
import com.pchpsky.diary.R
import com.pchpsky.diary.presentation.components.*
import com.pchpsky.diary.presentation.components.dropdownmenu.DiaryDropDownMenu
import com.pchpsky.diary.presentation.record.FakeRecordInsulinViewModel
import com.pchpsky.diary.presentation.record.RecordViewModel
import com.pchpsky.diary.presentation.record.insulin.interfacies.RecordInsulinViewModel
import com.pchpsky.diary.presentation.theme.DiaryTheme
import com.pchpsky.diary.presentation.theme.blue
import com.pchpsky.diary.presentation.theme.green
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
    val noteText = remember { mutableStateOf("") }

    LaunchedEffect(true) {
        viewModel.insulins()
    }

    @Composable
    fun Screen() {
        if (viewState.loading) return
        ConstraintLayout(
            modifier = Modifier
                .background(DiaryTheme.colors.background)
                .fillMaxSize()
                .imePadding()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    ) { focusManager.clearFocus(true) }
        ) {
            val (insulinUnits, timePicker, datePicker, dropDownMenu, noteTextField, addRecordButton)
            = createRefs()

            Units(
                units = viewState.units.toString(),
                modifier = Modifier
                    .constrainAs(insulinUnits) {
                        centerHorizontallyTo(parent)
                    },
                increment = { viewModel.incrementUnits() },
                decrement = {
                    viewModel.decrementUnits()
                },
                setUnits = { units -> viewModel.setUnits(units) }
            )

            Text(
                text = viewState.time,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .clickable { viewModel.showTimePicker(true) }
                    .constrainAs(timePicker) {
                        top.linkTo(insulinUnits.bottom, 20.dp)
                        start.linkTo(parent.start, 20.dp)
                    },
                color = DiaryTheme.colors.text,
                style = DiaryTheme.typography.pickers
            )

            Text(
                text = viewState.date,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .clickable { viewModel.showDatePicker(true) }
                    .constrainAs(datePicker) {
                        top.linkTo(insulinUnits.bottom, 20.dp)
                        end.linkTo(parent.end, 20.dp)
                    },
                color = DiaryTheme.colors.text,
                style = DiaryTheme.typography.pickers
            )

            DiaryDropDownMenu(
                modifier = Modifier
                    .constrainAs(dropDownMenu) {
                        top.linkTo(timePicker.bottom, 20.dp)
                        centerHorizontallyTo(parent)
                    },
                selectedInsulin = viewState.selectedInsulin,
                insulins = viewState.insulins,
                expanded = viewState.dropDownInsulinMenu,
                onClick = { viewModel.dropInsulinMenu(true) },
                select = { viewModel.selectInsulin(it) },
                dismiss = { viewModel.dropInsulinMenu(false) }
            )

            NoteTextField(
                value = noteText,
                modifier = Modifier.constrainAs(noteTextField) {
                    top.linkTo(dropDownMenu.bottom, 20.dp)
                    start.linkTo(parent.start, 20.dp)
                    end.linkTo(parent.end, 20.dp)
                    width = Dimension.fillToConstraints
                }
            )

            RoundedFilledButton(
                stringResource(R.string.add_record),
                modifier = Modifier
                    .constrainAs(addRecordButton) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start, 40.dp)
                        end.linkTo(parent.end, 40.dp)
                        width = Dimension.fillToConstraints
                    },
                color = green,
                onClick = {}
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
                viewModel.selectTime(it)
            }
        )

        DatePicker(
            show = viewState.showDatePicker,
            close = { viewModel.showDatePicker(false) },
            selectDate = {
                viewModel.selectDate(it)
            }
        )
    }
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
