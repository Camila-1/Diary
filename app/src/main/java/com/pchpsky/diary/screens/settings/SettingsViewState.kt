package com.pchpsky.diary.screens.settings

import com.pchpsky.diary.datasource.network.model.Insulin

data class SettingsViewState(
    var insulins: List<Insulin> = listOf(),
    var glucoseInit: String = "",
    var loading: Boolean = false,
)
