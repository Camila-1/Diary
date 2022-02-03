package com.pchpsky.diary.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pchpsky.diary.data.network.model.Insulin
import com.pchpsky.diary.presentation.theme.DiaryTheme
import com.pchpsky.diary.utils.extensions.toHex

@Composable
fun InsulinMenuItem(insulin: Insulin, onClick: (Insulin) -> Unit) {

    Row(
        modifier = Modifier
            .height(20.dp)
            .clickable { onClick(insulin) },
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = insulin.name,
            style = DiaryTheme.typography.body
        )
        ColorCircle(
            color = Color(android.graphics.Color.parseColor(insulin.color)),
            size = 20.dp,
            modifier = Modifier
        )
    }
}

@Composable
@Preview
fun InsulinMenuItemPreview() {
    DiaryTheme {
        InsulinMenuItem(insulin = Insulin("id", Color.Blue.toHex(), "Test Insulin")){}
    }
}