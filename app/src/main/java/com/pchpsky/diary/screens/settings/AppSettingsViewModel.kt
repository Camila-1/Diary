package com.pchpsky.diary.screens.settings

import androidx.lifecycle.ViewModel
import com.pchpsky.diary.datasourse.network.NetworkClient
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AppSettingsViewModel @Inject constructor(
    networkClient: NetworkClient
) : ViewModel() {
}