package com.pchpsky.diary.screens.launch

import android.util.Log
import com.pchpsky.diary.datasourse.network.NetworkClient
import com.pchpsky.schema.CurrentUserQuery
import kotlinx.coroutines.*


class AppLaunchRepository(private val networkClient: NetworkClient) : LaunchRepository {
    val scope = CoroutineScope(Job() + Dispatchers.IO)

    override fun user(): CurrentUserQuery.Data? {
        return runBlocking(Dispatchers.IO) {
            val responce = networkClient.user()
            Log.d("graphql_query", "$responce.currentUser")
            responce
        }
    }
}