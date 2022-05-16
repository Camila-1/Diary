package com.pchpsky.diary.presentation.recordinsulin


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.imePadding
import com.pchpsky.diary.data.network.model.Insulin
import com.pchpsky.diary.presentation.components.*
import com.pchpsky.diary.presentation.components.dropdownmenu.InsulinDropDownMenu
import com.pchpsky.diary.presentation.components.textfield.NoteTextField
import com.pchpsky.diary.presentation.recordinsulin.viewmodelinterface.RecordInsulinViewModel
import com.pchpsky.diary.presentation.theme.DiaryTheme
import com.pchpsky.diary.presentation.theme.blue
import com.pchpsky.diary.utils.extensions.toHex
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@SuppressLint("CoroutineCreationDuringComposition")
@ExperimentalComposeUiApi
@Composable
fun RecordInsulinScreen(
    viewModel: RecordInsulinViewModel = hiltViewModel<InsulinViewModel>(),
    onBackClick: () -> Unit
) {
    val viewState by viewModel.uiState.collectAsState()
    val focusManager = LocalFocusManager.current
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(true) {
        viewModel.insulins()
    }

    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = { snackbarHostState ->
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 10.dp),
                snackbar = { snackbarData ->
                    DiarySnackbar(snackbarData, blue, Modifier.padding(vertical = 10.dp, horizontal = 15.dp))
                }
            )
        },
        topBar = {
            RecordInsulinTopBar {
                onBackClick()
            }
        }
    ) {
        ConstraintLayout(
            modifier = Modifier
                .background(DiaryTheme.colors.background)
                .fillMaxSize()
                .imePadding()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                ) {
                    focusManager.clearFocus(true)
                }
        ) {
            val (insulin, recordButton, units, timePicker, datePicker, note) = createRefs()

            UnitsCounter(
                units = viewState.units,
                modifier = Modifier
                    .constrainAs(units) {
                        top.linkTo(parent.top)
                        centerHorizontallyTo(parent)
                    }
                    .padding(vertical = 24.dp),
                increment = { viewModel.incrementUnits() },
                decrement = { viewModel.decrementUnits() },
                setUnits = {}
            )

            Text(
                text = viewState.time,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .clickable { viewModel.showTimePicker(true) }
                    .constrainAs(timePicker) {
                        top.linkTo(units.bottom, 20.dp)
                        centerHorizontallyTo(parent)
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
                        top.linkTo(timePicker.bottom, 20.dp)
                        centerHorizontallyTo(parent)
                    },
                color = DiaryTheme.colors.text,
                style = DiaryTheme.typography.pickers
            )

            TimePicker(
                show = viewState.showTimePicker,
                close = { viewModel.showTimePicker(false) },
                selectTime = { viewModel.selectTime(it) }
            )

            DatePicker(
                show = viewState.showDatePicker,
                close = { viewModel.showDatePicker(false) },
                selectDate = { viewModel.selectDate(it) })

            Box(
                modifier = Modifier
                    .constrainAs(insulin) {
                        centerHorizontallyTo(parent)
                        top.linkTo(datePicker.bottom, 20.dp)
                        width = Dimension.percent(.7f)
                        height = Dimension.value(40.dp)
                    }
            ) {
                Insulin(
                    modifier = Modifier,
                    selectedInsulin = viewState.selectedInsulin,
                    onClick = {
                        viewModel.showInsulinMenu(true)
                    }
                )

                InsulinDropDownMenu(
                    modifier = Modifier,
                    expanded = viewState.showInsulinMenu,
                    items = viewState.insulins,
                    onItemSelected = {
                        viewModel.selectInsulin(it)
                    },
                    onDismiss = {
                        viewModel.showInsulinMenu(false)
                    }
                )
            }

            NoteTextField(
                value = viewState.note,
                modifier = Modifier
                    .constrainAs(note) {
                        top.linkTo(insulin.bottom, 20.dp)
                        centerHorizontallyTo(parent)
                    }
                    .padding(horizontal = 16.dp),
                onValueChanged = {
                    viewModel
                }
            )

            RoundedFilledButton(
                text = "Record",
                color = DiaryTheme.colors.secondary,
                modifier = Modifier
                    .constrainAs(recordButton) {
                        centerHorizontallyTo(parent)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.percent(.5f)
                    }
            ) {

            }
        }

        if (viewState.unitsInputError.isNotEmpty()) {
            scope.launch {
                scaffoldState.snackbarHostState.showSnackbar(viewState.unitsInputError)
            }
        }
    }
}

@Composable
fun Insulin(modifier: Modifier, selectedInsulin: Insulin?, onClick: () -> Unit) {
    Row(
        modifier = modifier
            .clickable { onClick() },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ColorCircle(
            color = Color(android.graphics.Color.parseColor(selectedInsulin?.color ?: "#ffffff")),
            size = 30.dp,
            modifier = Modifier
        )

        Text(
            text = selectedInsulin?.name ?: "No insulin added",
            style = DiaryTheme.typography.primaryHeader,
            color = Color.White,
            modifier = Modifier
                .padding(start = 8.dp),
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}


@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
@Preview
fun InsulinScreenPreview(
    @PreviewParameter(RecordInsulinScreenPreviewParameterProvider::class) viewModel: RecordInsulinViewModel) {
    DiaryTheme {
        RecordInsulinScreen(viewModel) {}
    }
}

class RecordInsulinScreenPreviewParameterProvider :
    PreviewParameterProvider<RecordInsulinViewModel> {

    override val values: Sequence<RecordInsulinViewModel> = sequenceOf(
        object : RecordInsulinViewModel {
            override val uiState: StateFlow<RecordInsulinViewState> =
                MutableStateFlow(
                    RecordInsulinViewState()
                        .copy(selectedInsulin = Insulin("id", Color.Blue.toHex(), "Test Insulin"))
                )

            override fun decrementUnits() {}
            override fun incrementUnits() {}
            override fun setUnits(units: String) {}
            override suspend fun insulins() {}
            override fun selectInsulin(insulin: Insulin) {}
            override fun showInsulinMenu(drop: Boolean) {}
            override fun showTimePicker(show: Boolean) {}
            override fun showDatePicker(show: Boolean) {}
            override fun selectTime(localTime: String) {}
            override fun selectDate(localDate: String) {}
            override fun addNote(note: String) {}
        }
    )
}
