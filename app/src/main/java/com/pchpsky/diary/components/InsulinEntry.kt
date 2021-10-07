package com.pchpsky.diary.components

import android.graphics.Color.parseColor
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pchpsky.diary.datasource.network.model.Insulin
import com.pchpsky.diary.theme.DiaryTheme

@Composable
fun InsulinEntry(insulin: Insulin) {
    Card(
        backgroundColor = DiaryTheme.colors.cardBackground,
        modifier = Modifier
            .wrapContentSize(Alignment.Center),
        shape = DiaryTheme.shapes.roundedCard
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
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
}