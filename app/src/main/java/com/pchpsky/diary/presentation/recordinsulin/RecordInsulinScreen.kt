package com.pchpsky.diary.presentation.recordinsulin


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.imePadding
import com.pchpsky.diary.presentation.components.*
import com.pchpsky.diary.presentation.components.circularlist.CircularList
import com.pchpsky.diary.presentation.components.circularlist.rememberCircularListState
import com.pchpsky.diary.presentation.recordinsulin.viewmodelinterface.RecordInsulinViewModel
import com.pchpsky.diary.presentation.theme.DiaryTheme
import com.pchpsky.diary.presentation.theme.blue
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
    val insulinCircularListState = rememberCircularListState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(true) {
        viewModel.insulins()
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
            val (insulinMenuButton, recordButton, circularList) = createRefs()

            InsulinMenuButton(
                selectedInsulin = viewState.selectedInsulin!!,
                Modifier
                    .constrainAs(insulinMenuButton) {
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom, 250.dp)

                    }
            ) {
                insulinCircularListState.visible = true
            }

            CircularList(
                visibleItems = 5,
                visible = viewState.showInsulinMenu,
                circularFraction = 1.1f,
                overshootItems = 1,
                state = insulinCircularListState,
                modifier = Modifier
                    .height(150.dp)
                    .width(200.dp)
                    .background(Color.Red)
                    .constrainAs(circularList) {
                        end.linkTo(insulinMenuButton.start)
                    }
            ) {
                viewState.insulins.forEach {
                    InsulinMenuItem(insulin = it) {
                        viewModel.selectInsulin(it)
                    }
                }
            }

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



@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
@Preview
fun InsulinScreenPreview() {
    DiaryTheme {
        RecordInsulinScreen(FakeRecordInsulinViewModel) {}
    }
}
