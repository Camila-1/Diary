package com.pchpsky.diary.components

import android.graphics.Color.parseColor
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pchpsky.diary.datasource.network.model.Insulin
import com.pchpsky.diary.theme.DiaryTheme

@Composable
fun InsulinEntry(insulin: Insulin, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                indication = LocalIndication.current,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onClick()
            }
        ,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        InsulinColorCircle(
            color = Color(parseColor(insulin.color)),
            modifier = Modifier
                .align(Alignment.Bottom)
        )
        Text(
            text = insulin.name,
            color = DiaryTheme.colors.text,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}
