package com.pchpsky.diary.presentation.components

import android.widget.ImageButton
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Icecream
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pchpsky.diary.data.network.model.Insulin
import com.pchpsky.diary.presentation.theme.DiaryTheme
import com.pchpsky.diary.utils.extensions.toHex

@Composable
fun InsulinMenuButton(selectedInsulin: Insulin, onClick: () -> Unit) {
    IconButton(
        onClick = { onClick() },
        modifier = Modifier
    ) {
        Icon(
            imageVector = Icons.Filled.Eco,
            contentDescription = "insulin_menu_button",
            modifier = Modifier
                .background(Color(android.graphics.Color.parseColor(selectedInsulin.color)), shape = CircleShape)
                .size(70.dp)
                .padding(10.dp),
            tint = Color.White
        )
    }


}



@Composable
@Preview
fun InsulinMenuButtonPreview() {
    DiaryTheme {
        InsulinMenuButton(Insulin("id", Color.Blue.toHex(), "Test Insulin")){}
    }
}