package com.pchpsky.diary.datasource.network.model

data class Insulin(
    val id: String,
    val name: String,
    val color: Color
)

data class Color(
    val red: Float,
    val green: Float,
    val blue: Float
)
