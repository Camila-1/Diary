package com.pchpsky.diary.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.pchpsky.diary.data.network.model.Insulin

@Composable
fun AnimatedInsulinMenu(insulins: List<Insulin>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = PaddingValues(vertical = 20.dp, horizontal = 20.dp)
    ) {
        items(insulins.size) { insulin ->
            InsulinMenuItem(insulin = insulin)
        }
    }
}