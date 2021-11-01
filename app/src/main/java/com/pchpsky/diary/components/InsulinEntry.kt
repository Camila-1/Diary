package com.pchpsky.diary.components

import android.graphics.Color.parseColor
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pchpsky.diary.datasource.network.model.Insulin
import com.pchpsky.diary.theme.DiaryTheme

@Composable
fun InsulinEntry(insulin: Insulin) {
    Row(
        modifier = Modifier.fillMaxWidth(),
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
