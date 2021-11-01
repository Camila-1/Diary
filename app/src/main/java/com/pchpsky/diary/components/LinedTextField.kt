package com.pchpsky.diary.components

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.android.animation.SegmentType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pchpsky.diary.theme.DiaryTheme

@Composable
fun LinedTextField(value: MutableState<String>, modifier: Modifier, placeHolder: String) {

    TextField(
        value = value.value,
        onValueChange = {
            value.value = it
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = DiaryTheme.colors.focusedInputFieldBorder,
            unfocusedBorderColor = DiaryTheme.colors.unfocusedInputFieldBorder,
            textColor = DiaryTheme.colors.inputText,
            cursorColor = DiaryTheme.colors.inputText
        ),
        modifier = modifier,
        placeholder = {
            Text(
                text = placeHolder,
                color = DiaryTheme.colors.inputText,
                style = DiaryTheme.typography.textField
            )
        },
        singleLine = true,
        textStyle = DiaryTheme.typography.textField
    )

    val isInFocus = remember { mutableStateOf(false) }

    BasicTextField(
        value = value.value,
        onValueChange = {
            value.value = it
            isInFocus.value = true
            },
        modifier = modifier.height(40.dp),
        decorationBox = { textField ->

            Column(modifier = Modifier.height(30.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    textField()
                    if (value.value.isEmpty()) {
                        Text(
                            text = "Name",
                            color = Color.Gray,
                            fontSize = 16.sp,
                            modifier = Modifier
                                .align(Alignment.Bottom)
                                .padding(start = 4.dp, bottom = 4.dp)
                                .background(Color.Yellow)
                        )
                    }

                }

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background( if (isInFocus.value) Color.Red else Color.Gray )
                            .height(1.dp)
                    )
                }
            }


        },
        textStyle = DiaryTheme.typography.textField,
        cursorBrush = SolidColor(Color.Gray),
        maxLines = 1,
        singleLine = true
    )
}