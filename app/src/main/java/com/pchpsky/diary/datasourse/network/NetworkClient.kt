package com.pchpsky.diary.datasourse.network

import android.util.Log
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
import com.pchpsky.schema.CurrentUserQuery

class NetworkClient(private val apolloClient: ApolloClient) {

    suspend fun user(): CurrentUserQuery.Data? {
        return try {
            apolloClient.query(CurrentUserQuery()).await().data
        } catch (exception: ApolloException) {
            Log.e("APOLLO_EXCEPTION", exception.localizedMessage ?: "")
            null
        }
    }
}