package com.pchpsky.diary.presentation.recordinsulin


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
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
            val (insulinMenu, recordButton) = createRefs()

            InsulinMenu(
                modifier = Modifier
                    .constrainAs(insulinMenu) {
                        centerHorizontallyTo(parent)
                        bottom.linkTo(parent.top)
                        width = Dimension.percent(.5f)
                        height = Dimension.value(40.dp)
                    },
                selectedInsulin = viewState.selectedInsulin,
                onClick = { /*TODO*/ }
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
fun InsulinMenu(modifier: Modifier, selectedInsulin: Insulin?, onClick: () -> Unit) {
    Row(
        modifier = modifier
            .clickable { onClick() }
    ) {
        ColorCircle(
            color = Color(android.graphics.Color.parseColor(selectedInsulin?.color)),
            size = 30.dp,
            modifier = Modifier
        )

        Text(
            text = selectedInsulin?.name ?: "No insulin added",
            style = DiaryTheme.typography.body,
            color = Color.White,
            modifier = Modifier,
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
            override fun setUnits(points: String) {}
            override suspend fun insulins() {}
            override fun selectInsulin(insulin: Insulin) {}
            override fun showInsulinMenu(drop: Boolean) {}
            override fun showTimePicker(show: Boolean) {}
            override fun showDatePicker(show: Boolean) {}
            override fun selectTime(time: String) {}
            override fun selectDate(date: String) {}
        }
    )
}
