package com.pchpsky.diary.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pchpsky.diary.R
import com.pchpsky.diary.theme.DiaryTheme

@Composable
fun LogoGroup(modifier: Modifier) {
    val image = painterResource(R.drawable.ic_logo)
    Row(verticalAlignment = Alignment.Bottom, modifier = modifier) {
        Image(painter = image, contentDescription = null, modifier = Modifier.width(83.dp))
        Text(
            text = "Diary",
            style = DiaryTheme.typography.logo,
            modifier = Modifier.padding(bottom = 10.dp, start = 15.dp),
            color = DiaryTheme.colors.logo
        )
    }
}