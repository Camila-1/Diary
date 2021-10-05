package com.pchpsky.diary.extensions

import android.graphics.Color.parseColor
import androidx.compose.ui.graphics.Color
import com.pchpsky.diary.datasource.network.model.Insulin
import com.pchpsky.schema.CreateInsulinMutation

fun CreateInsulinMutation.Data.insulin(): Insulin {
    return Insulin(
        id = insulin?.id!!,
        color = insulin.color!!,
        name = insulin.name!!
    )
}