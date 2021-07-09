package com.pchpsky.diary.ui.launch

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LaunchViewModel @Inject constructor(private val repository: LaunchRepository) : ViewModel() {



    suspend fun userExists(): Boolean {
        return withContext(Dispatchers.IO) {
            repository.user() != null
        }
    }
}