package com.pchpsky.diary.screens.settings.ui

import android.annotation.SuppressLint
import android.content.Context
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pchpsky.diary.components.*
import com.pchpsky.diary.datasource.network.model.Insulin
import com.pchpsky.diary.extensions.toHex
import com.pchpsky.diary.screens.settings.FakeSettingsViewModel
import com.pchpsky.diary.screens.settings.GlucoseUnits
import com.pchpsky.diary.screens.settings.EditInsulinDialog
import com.pchpsky.diary.screens.settings.interfaces.SettingsViewModel
import com.pchpsky.diary.theme.DiaryTheme
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import kotlinx.coroutines.launch
import android.graphics.Color.parseColor

@ExperimentalComposeUiApi
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Settings(
    navController: NavHostController,
    viewModel: SettingsViewModel
) {
    val scope = rememberCoroutineScope()
    val viewState by viewModel.uiState.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

    scope.launch {
        viewModel.settings()
    }

    @Composable
    fun Screen() {
        ProgressBar(viewState.loading)
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxSize()
                .background(DiaryTheme.colors.background)
                .padding(30.dp),
        ) {

            item {
                Glucose(viewState.glucoseInit) {
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
                    onEdit = { name, color ->
                        viewModel.showAddInsulinDialog(true, name, color)
                    }
                )
            }
        }
    }

    ShowDialog(
        dialog = viewState.editInsulinDialog,
        onDismiss = { viewModel.showAddInsulinDialog(false) }
    ) { color, name ->
        keyboardController?.hide()
        scope.launch {
            viewModel.addInsulin(color, name)
        }
    }

    Screen()
}

@Composable
fun Glucose(unit: String, onClick: (GlucoseUnits) -> Unit) {

    var glucoseUnit = unit

    Column {
        CategoryHeader(modifier = Modifier.padding(bottom = 20.dp),"Glucose")

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
                    modifier = Modifier.size(30.dp)
                )

                Text(
                    text = it.unit,
                    modifier = Modifier.padding(start = 25.dp).align(Alignment.CenterVertically),
                    style = DiaryTheme.typography.body,
                    color = Color.White,
                )
            }
        }
    }
}

@Composable
fun Insulin(insulins: List<Insulin>, onEdit: (String, Color) -> Unit) {

    Column(
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CategoryHeader(modifier = Modifier, "Insulin")

            Icon(
                imageVector = Icons.Rounded.AddCircle,
                contentDescription = null,
                modifier = Modifier
                    .size(35.dp)
                    .clickable(
                        indication = LocalIndication.current,
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = { onEdit("", Color(Color.Yellow.toArgb())) }
                    ),
                tint = DiaryTheme.colors.primary,
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            insulins.forEach { insulin ->
                InsulinEntry(
                    insulin = insulin,
                    onEdit = { onEdit(insulin.name, Color(parseColor(insulin.color))) },
                    onDelete = {}
                )
            }
        }


    }
}

private fun showToastMessage(context: Context, message:String){
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
fun ShowDialog(dialog: EditInsulinDialog, onDismiss: () -> Unit, onAddClick: (String, String) -> Unit) {
    if (!dialog.show) return
    Dialog(
        onDismissRequest = { onDismiss() },
    ) {
        val insulinName = remember { mutableStateOf(dialog.insulinName) }
        val insulinColor = remember { mutableStateOf(dialog.insulinColor) }
        Column(
            modifier = Modifier.background(Color.DarkGray).fillMaxWidth()
        ) {
            Text(
                if (dialog.insulinName.isEmpty()) "Add Insulin" else "Edit Insulin",
                color = DiaryTheme.colors.text,
                modifier = Modifier.padding(start = 10.dp, top = 20.dp),
                style = DiaryTheme.typography.primaryHeader
            )

            val dialogState = rememberMaterialDialogState()
            ColorPicker(dialogState, insulinColor)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, top = 20.dp, end = 20.dp, bottom = 20.dp),
            ) {

                InsulinColorCircle(
                    insulinColor.value,
                    modifier = Modifier
                        .align(Alignment.Bottom)
                        .clickable { dialogState.show() }
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

                RoundedOutlinedButton(
                    text = "Cancel",
                    modifier = Modifier.padding(10.dp),
                    onClick = { onDismiss() }
                )

                RoundedFilledButton(
                    text = "Save",
                    color = DiaryTheme.colors.primary,
                    modifier = Modifier.padding(10.dp),
                    onClick = {
                        onAddClick(insulinColor.value.toHex(), insulinName.value)
                        onDismiss()
                    }
                )
            }


        }
    }
}

@ExperimentalComposeUiApi
@Preview
@Composable
fun SettingsPreview() {
    DiaryTheme {
        Settings(rememberNavController(), FakeSettingsViewModel)
    }
}

