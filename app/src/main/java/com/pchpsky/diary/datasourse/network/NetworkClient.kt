package com.pchpsky.diary.datasourse.network

import android.util.Log
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
import com.pchpsky.schema.CreateUserMutation
import com.pchpsky.schema.CurrentUserQuery
import java.lang.Exception
import javax.inject.Inject

class NetworkClient @Inject constructor(private val apolloClient: ApolloClient) {

    suspend fun user(): CurrentUserQuery.Data? {
        return try {
            apolloClient.query(CurrentUserQuery()).await().data
        } catch (exception: ApolloException) {
            Log.e("APOLLO_EXCEPTION", exception.localizedMessage ?: "")
            null
        }
    }

    suspend fun createUser(email: String, password: String): CreateUserMutation.Data? {
        return try {
            apolloClient.mutate(CreateUserMutation(email, password)).await().data
        } catch (exception: Exception) {
            Log.e("APOLLO_EXCEPTION", exception.localizedMessage ?: "")
            null
        }
    }
}