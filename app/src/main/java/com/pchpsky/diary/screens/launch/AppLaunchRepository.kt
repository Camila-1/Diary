package com.pchpsky.diary.screens.launch

import arrow.core.Either
import com.pchpsky.diary.datasourse.localstorage.DataStoreManager
import com.pchpsky.diary.datasourse.network.NetworkClient
import com.pchpsky.diary.exceptions.NetworkError
import com.pchpsky.schema.CurrentUserQuery
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow



class AppLaunchRepository(
    private val networkClient: NetworkClient,
    private val dataStoreManager: DataStoreManager
    ) : LaunchRepository {

    val scope = CoroutineScope(Job() + Dispatchers.IO)

    override suspend fun user(): Either<NetworkError, CurrentUserQuery.Data?> {
        return scope.async {
            networkClient.user()
        }.await()
    }

    override fun token(): Flow<String?> = dataStoreManager.userToken()
}