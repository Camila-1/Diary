package com.pchpsky.diary.screens.launch

import androidx.compose.runtime.referentialEqualityPolicy
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LaunchViewModel @Inject constructor(private val repository: LaunchRepository) : ViewModel() {



    suspend fun userExists(): Boolean {
        return withContext(Dispatchers.IO) {
            repository.user() != null
        }
    }
}