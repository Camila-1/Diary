package com.pchpsky.diary.screens.launch

import android.util.Log
import arrow.core.Either
import com.pchpsky.diary.datasourse.network.NetworkClient
import com.pchpsky.diary.exceptions.NetworkError
import com.pchpsky.schema.CurrentUserQuery
import kotlinx.coroutines.*


class AppLaunchRepository(private val networkClient: NetworkClient) : LaunchRepository {
    val scope = CoroutineScope(Job() + Dispatchers.IO)

    override fun user(): Either<NetworkError, CurrentUserQuery.Data?> {
        return runBlocking(Dispatchers.IO) {
            val response = networkClient.user()
            Log.d("graphql_query", "$response.currentUser")
            response
        }
    }
}