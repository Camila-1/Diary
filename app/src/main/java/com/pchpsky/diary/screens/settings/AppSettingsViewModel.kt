package com.pchpsky.diary.screens.settings

import androidx.lifecycle.ViewModel
import com.pchpsky.diary.datasourse.network.NetworkClient
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

enum class GlucoseUnits{
    MG_PER_DL,
    MMOL_PER_L
}

@HiltViewModel
class AppSettingsViewModel @Inject constructor(
    repository: SettingsRepository
) : ViewModel() {
}