package com.pchpsky.diary.presentation.components

import android.graphics.Color.parseColor
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pchpsky.diary.data.network.model.Insulin
import com.pchpsky.diary.presentation.theme.DiaryTheme

@Composable
fun InsulinEntry(insulin: Insulin, update: () -> Unit, delete: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
        ,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        ColorCircle(
            color = Color(parseColor(insulin.color)),
            size = 30.dp,
            modifier = Modifier
                .align(Alignment.Bottom)
        )

        Text(
            text = insulin.name,
            color = DiaryTheme.colors.text,
            modifier = Modifier.align(Alignment.CenterVertically).weight(10f),
            maxLines = 1
        )

        Icon(
            imageVector = Icons.Rounded.Edit,
            contentDescription = null,
            modifier = Modifier
                .weight(1f)
                .clickable {
                    update()
                },
            tint = Color.White
        )

        Icon(
            imageVector = Icons.Rounded.Delete,
            contentDescription = null,
            modifier = Modifier
                .weight(1f)
                .clickable {
                    delete()
                },
            tint = Color.White
        )
    }
}
