package com.pchpsky.diary.presentation.recordinsulin


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.pchpsky.diary.data.network.model.Insulin
import com.pchpsky.diary.presentation.components.*
import com.pchpsky.diary.presentation.components.dropdownmenu.DiaryDropDownMenu
import com.pchpsky.diary.presentation.recordinsulin.interfacies.RecordInsulinViewModel
import com.pchpsky.diary.presentation.theme.DiaryTheme
import com.pchpsky.diary.presentation.theme.blue
import com.pchpsky.diary.presentation.theme.green
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
    val noteText = remember { mutableStateOf("") }

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
                ) { focusManager.clearFocus(true) }
        ) {
            val insulinMenuButton = createRef()

            InsulinMenuButton(selectedInsulin = viewState.selectedInsulin!!) {

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
