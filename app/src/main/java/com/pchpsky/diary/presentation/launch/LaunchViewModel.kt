package com.pchpsky.diary.presentation.launch

import androidx.lifecycle.ViewModel
import arrow.core.Either
import com.pchpsky.diary.data.network.exceptions.NetworkError
import com.pchpsky.diary.presentation.launch.interfaces.LaunchRepository
import com.pchpsky.diary.presentation.launch.interfaces.LaunchViewModel
import com.pchpsky.schema.CurrentUserQuery
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class LaunchViewModel @Inject constructor(private val repository: LaunchRepository) : ViewModel(),
    LaunchViewModel {

    override suspend fun userLoggedIn(): Either<NetworkError, CurrentUserQuery.Data?> {
        return repository.user()
    }

    override suspend fun token(): String? {
        return repository.token().stateIn(CoroutineScope(Dispatchers.IO)).value
    }
}