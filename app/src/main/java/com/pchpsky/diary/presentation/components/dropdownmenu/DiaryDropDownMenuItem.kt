package com.pchpsky.diary.presentation.components.dropdownmenu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.pchpsky.diary.data.network.model.Insulin
import com.pchpsky.diary.presentation.components.InsulinColorCircle
import com.pchpsky.diary.presentation.theme.DiaryTheme

@Composable
fun DiaryDropDownMenuItem(insulin: Insulin) {

    Row(
        horizontalArrangement = Arrangement.Center
    ) {
        InsulinColorCircle(
            color = Color(android.graphics.Color.parseColor(insulin.color)),
            modifier = Modifier
                .align(Alignment.Bottom)
        )

        Text(
            text = insulin.name,
            style = DiaryTheme.typography.body,
            color = Color.White,
            modifier = Modifier.padding(start = 15.dp),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}