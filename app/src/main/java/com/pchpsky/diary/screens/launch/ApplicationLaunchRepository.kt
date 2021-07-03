package com.pchpsky.diary.screens.launch

import android.util.Log
import com.pchpsky.diary.datasourse.network.NetworkClient
import com.pchpsky.schema.CurrentUserQuery
import kotlinx.coroutines.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class ApplicationLaunchRepository(private val networkClient: NetworkClient) : LaunchRepository {
    val scope = CoroutineScope(Job() + Dispatchers.IO)

    override suspend fun user(): CurrentUserQuery.Data? {
        return withContext(Dispatchers.IO) {
            val responce = networkClient.user()
            Log.d("graphql_query", "$responce.currentUser")
            responce
        }
    }
}