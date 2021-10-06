package com.pchpsky.diary.extensions

import android.graphics.Color.parseColor
import androidx.compose.ui.graphics.Color
import com.pchpsky.diary.datasource.network.model.Insulin
import com.pchpsky.schema.CreateInsulinMutation
import com.pchpsky.schema.InsulinsQuery

fun CreateInsulinMutation.Data.insulin(): Insulin {
    return Insulin(
        id = insulin?.id!!,
        color = insulin.color!!,
        name = insulin.name!!
    )
}

fun InsulinsQuery.Data.insulins(): List<Insulin>? {
    return settings?.insulins?.map {
        Insulin(
            id = it?.id!!,
            color = it.color!!,
            name = it.name!!
        )
    }
}