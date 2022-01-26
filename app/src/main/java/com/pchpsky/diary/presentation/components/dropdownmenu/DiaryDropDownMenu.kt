package com.pchpsky.diary.presentation.components.dropdownmenu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pchpsky.diary.data.network.model.Insulin
import com.pchpsky.diary.presentation.components.ColorCircle
import com.pchpsky.diary.presentation.theme.DiaryTheme
import com.pchpsky.diary.utils.extensions.toHex

@Composable
fun DiaryDropDownMenu(
    modifier: Modifier,
    selectedInsulin: Insulin?,
    insulins: List<Insulin>,
    expanded: Boolean,
    onClick: () -> Unit,
    select: (Insulin) -> Unit,
    dismiss: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth(.6f),
        contentAlignment = Alignment.Center
    ) {
        if (selectedInsulin != null) {
            Row(
                modifier = Modifier
                    .clickable { onClick() }

                    .padding(end =15.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ColorCircle(
                    color = Color(android.graphics.Color.parseColor(selectedInsulin.color)),
                    size = 30.dp,
                    modifier = Modifier
                )

                Text(
                    text = selectedInsulin.name,
                    style = DiaryTheme.typography.body,
                    color = Color.White,
                    modifier = Modifier,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = "drop down icon",
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.CenterEnd),
                tint = Color.White
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { dismiss() },
            modifier = Modifier
                .background(Color.DarkGray)

        ) {
            insulins.forEach {
                DropdownMenuItem(
                    onClick = {
                        select(it)
                        dismiss()
                    },
                    modifier = Modifier
                ) {
                    DiaryDropDownMenuItem(insulin = it)
                }
            }
        }
    }
}

@Composable
@Preview
fun DropDownMenuPreview() {
    DiaryTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 150.dp)
        ) {
            DiaryDropDownMenu(
                modifier = Modifier
                    .align(Alignment.Center),
                selectedInsulin = Insulin("id", Color.Blue.toHex(), "Test Insulinjfjfhfjhfhdfhhjfhjhhfjhhj"),
                insulins = listOf(
                    Insulin("id", Color.Blue.toHex(), "Test Insulin"),
                    Insulin("id", Color.Red.toHex(), "Test Insulin test test test"),
                    Insulin("id", Color.Green.toHex(), "Test Insulin 3"),
                ),
                expanded = true,
                onClick = { /*TODO*/ },
                select = {}
            ) {}
        }
    }
}