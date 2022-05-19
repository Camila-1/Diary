package com.pchpsky.diary.presentation.components

import android.graphics.Color.parseColor
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pchpsky.diary.data.network.model.Insulin
import com.pchpsky.diary.presentation.theme.DiaryTheme
import com.pchpsky.diary.utils.extensions.toHex

@Composable
fun InsulinMenuItem(modifier: Modifier, insulin: Insulin?, onClick: (Insulin?) -> Unit) {

    Column(
        modifier = modifier
            .clickable { onClick(insulin) }
    ) {
        Text(
            text = insulin?.name ?: "No insulin added",
            style = DiaryTheme.typography.primaryHeader,
            color = Color.White,
            modifier = modifier
                .padding(start = 8.dp),
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Box(
            modifier = Modifier
                .background(Color(parseColor(insulin?.color ?: "#ffffff")))
                .fillMaxWidth()
                .height(4.dp)
        )
    }
}

@Composable
@Preview
fun InsulinMenuItemPreview() {
    DiaryTheme {
        InsulinMenuItem(
            modifier = Modifier,
            insulin = Insulin("id", Color.Blue.toHex(), "Test Insulin"),
            onClick = {}
        )
    }
}