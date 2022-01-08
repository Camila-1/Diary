package com.pchpsky.diary.presentation.settings.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color.parseColor
import android.widget.Toast
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.pchpsky.diary.presentation.components.*
import com.pchpsky.diary.data.network.model.Insulin
import com.pchpsky.diary.extensions.toHex
import com.pchpsky.diary.presentation.settings.*
import com.pchpsky.diary.presentation.settings.interfaces.SettingsViewModel
import com.pchpsky.diary.presentation.theme.DiaryTheme
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Settings(
    viewModel: SettingsViewModel = hiltViewModel<UserSettingsViewModel>(),
    onBackClick: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val viewState by viewModel.uiState.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(true) {
        viewModel.settings()
    }

    @Composable
    fun Screen() {
        if (viewState.loading) return
        ProgressBar(viewState.loading)
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(DiaryTheme.colors.background)
                .padding(30.dp),
        ) {

            item {
                Glucose(viewState.glucoseUnit) {
                    scope.launch {
                        viewModel.updateGlucoseUnit(it)
                    }
                }
            }

            item {
                Divider(
                    color = DiaryTheme.colors.divider,
                    thickness = 1.dp
                )
            }
            item {
                Insulin(
                    insulins = viewState.insulins,
                    add = { name, color ->
                        viewModel.showAddInsulinDialog(true, name, color)
                    },
                    update = { id, name, color ->
                        viewModel.showUpdateInsulinDialog(true, id, name, color)
                    },
                    delete = { id ->
                        viewModel.showDeleteInsulinDialog(true, id)
                    }
                )
            }
        }
    }



    Scaffold(
        topBar = { SettingsTopBar {
            onBackClick()
        } }
    ) {
        ProgressBar(viewState.loading)
        Screen()
    }


    AddInsulinDialog(
        dialogState = viewState.addInsulinDialogStateState,
        onDismiss = { viewModel.showAddInsulinDialog(false) }
    ) { color, name ->
        keyboardController?.hide()
        scope.launch {
            viewModel.addInsulin(color, name)
        }
    }

    UpdateInsulinDialog(
        dialogState = viewState.updateInsulinDialogStateState,
        onDismiss = { viewModel.showUpdateInsulinDialog(false) }
    ) { id, name, color ->
        keyboardController?.hide()
        scope.launch {
            viewModel.updateInsulin(id, color, name)
        }
    }

    DeleteInsulinDialog(
        dialogState = viewState.deleteInsulinDialogStateState,
        delete = { id ->
            scope.launch {
                viewModel.deleteInsulin(id)
            }
        },
        onDismiss = { viewModel.showDeleteInsulinDialog(false) }
    )
}

@Composable
fun Glucose(unit: String, onClick: (GlucoseUnits) -> Unit) {

    var glucoseUnit = unit

    Column {
        CategoryHeader(modifier = Modifier.padding(bottom = 20.dp), "Glucose")

        GlucoseUnits.units.forEach {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent, DiaryTheme.shapes.roundedButton)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        glucoseUnit = it.unit
                        onClick(it)
                    }
            ) {
                RadioButton(
                    selected = glucoseUnit == it.unit,
                    onClick = {
                        glucoseUnit = it.unit
                        onClick(it)
                    },
                    enabled = true,
                    colors = RadioButtonDefaults.colors(
                        selectedColor = DiaryTheme.colors.primary,
                        unselectedColor = Color.White
                    ),
                    modifier = Modifier
                        .size(30.dp)
                        .testTag(it.unit)
                )

                Text(
                    text = it.unit,
                    modifier = Modifier
                        .padding(start = 25.dp)
                        .align(Alignment.CenterVertically),
                    style = DiaryTheme.typography.body,
                    color = Color.White,
                )
            }
        }
    }
}

@Composable
fun Insulin(
    insulins: List<Insulin>,
    add: (String, Color) -> Unit,
    update: (id: String, name: String, color: Color) -> Unit,
    delete: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CategoryHeader(modifier = Modifier, "Insulin")
            AddInsulinButton { add("", Color(Color.Yellow.toArgb())) }
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            insulins.forEach { insulin ->
                InsulinEntry(
                    insulin = insulin,
                    update = { update(insulin.id, insulin.name, Color(parseColor(insulin.color))) },
                    delete = { delete(insulin.id) }
                )
            }
        }
    }
}

private fun showToastMessage(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Composable
fun CategoryHeader(modifier: Modifier, text: String) {
    Text(
        text = text,
        modifier = modifier,
        style = DiaryTheme.typography.primaryHeader,
        color = DiaryTheme.colors.text,
    )
}

@Composable
fun AddInsulinDialog(
    dialogState: AddInsulinDialogState,
    onDismiss: () -> Unit,
    add: (color: String, name: String) -> Unit,
) {
    if (!dialogState.show) return
    Dialog(
        onDismissRequest = { onDismiss() },
    ) {
        val insulinName = remember { mutableStateOf(dialogState.insulinName) }
        val insulinColor = remember { mutableStateOf(dialogState.insulinColor) }
        Column(
            modifier = Modifier
                .background(Color.DarkGray)
                .fillMaxWidth()
        ) {
            DialogTitle("Add Insulin")

            val colorPickerDialogState = rememberMaterialDialogState()
            ColorPicker(colorPickerDialogState, insulinColor)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 20.dp, end = 20.dp, bottom = 20.dp),
            ) {

                InsulinColorCircle(
                    insulinColor.value,
                    modifier = Modifier
                        .align(Alignment.Bottom)
                        .clickable { colorPickerDialogState.show() }
                )

                LinedTextField(
                    value = insulinName,
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .weight(10f),
                    placeHolder = "Name"
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {

                CancelButton { onDismiss() }

                PositiveButton("Add") {
                    add(insulinColor.value.toHex(), insulinName.value)
                    onDismiss()
                }
            }
        }
    }
}

@Composable
fun DeleteInsulinDialog(
    dialogState: DeleteInsulinDialogState,
    delete: (String) -> Unit,
    onDismiss: () -> Unit
) {
    if (!dialogState.show) return
    Dialog(
        onDismissRequest = { onDismiss() }
    ) {
        Column(
            modifier = Modifier
                .background(Color.DarkGray)
                .fillMaxWidth()
        ) {
            DialogTitle("Are you sure you want to delete?")

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                CancelButton { onDismiss() }

                PositiveButton("Delete") {
                    delete(dialogState.insulinId)
                    onDismiss()
                }
            }
        }
    }
}

@Composable
fun UpdateInsulinDialog(
    dialogState: UpdateInsulinDialogState,
    onDismiss: () -> Unit,
    update: (id: String, name: String, color: String) -> Unit
) {
    if (!dialogState.show) return
    Dialog(
        onDismissRequest = { onDismiss() },
    ) {
        val insulinName = remember { mutableStateOf(dialogState.insulinName) }
        val insulinColor = remember { mutableStateOf(dialogState.insulinColor) }
        Column(
            modifier = Modifier
                .background(Color.DarkGray)
                .fillMaxWidth()
        ) {
            DialogTitle("Update Insulin")

            val colorPickerDialogState = rememberMaterialDialogState()
            ColorPicker(colorPickerDialogState, insulinColor)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 20.dp, end = 20.dp, bottom = 20.dp),
            ) {

                InsulinColorCircle(
                    insulinColor.value,
                    modifier = Modifier
                        .align(Alignment.Bottom)
                        .clickable { colorPickerDialogState.show() }
                )

                LinedTextField(
                    value = insulinName,
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .weight(10f),
                    placeHolder = "Name"
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {

                CancelButton { onDismiss() }

                PositiveButton(
                    text = "Save"
                ) {
                    update(dialogState.insulinId, insulinColor.value.toHex(), insulinName.value)
                    onDismiss()
                }
            }
        }
    }
}

@Composable
fun DialogTitle(text: String) {
    Text(
        text = text,
        color = DiaryTheme.colors.text,
        modifier = Modifier.padding(start = 10.dp, top = 20.dp),
        style = DiaryTheme.typography.primaryHeader
    )
}

@Composable
fun CancelButton(onClick: () -> Unit) {
    RoundedOutlinedButton(
        text = "Cancel",
        modifier = Modifier.padding(10.dp),
        onClick = { onClick() }
    )
}

@Composable
fun PositiveButton(text: String, onClick: () -> Unit) {
    RoundedFilledButton(
        text = text,
        color = DiaryTheme.colors.primary,
        modifier = Modifier.padding(10.dp),
        onClick = { onClick() }
    )
}

@Composable
fun AddInsulinButton(onClick: () -> Unit) {
    Icon(
        imageVector = Icons.Rounded.AddCircle,
        contentDescription = null,
        modifier = Modifier
            .size(35.dp)
            .clickable(
                indication = LocalIndication.current,
                interactionSource = remember { MutableInteractionSource() },
                onClick = { onClick() }
            ),
        tint = DiaryTheme.colors.primary,
    )
}

@ExperimentalComposeUiApi
@Preview
@Composable
fun SettingsPreview() {
    DiaryTheme {
        Settings(FakeSettingsViewModel) {}
    }
}

