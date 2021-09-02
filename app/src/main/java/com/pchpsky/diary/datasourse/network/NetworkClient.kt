package com.pchpsky.diary.datasourse.network

import arrow.core.Either
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.await
import com.pchpsky.diary.exceptions.NetworkError
import com.pchpsky.diary.exceptions.handlers.NetworkErrorHandler
import com.pchpsky.schema.CreateSessionMutation
import com.pchpsky.schema.CreateUserMutation
import com.pchpsky.schema.CurrentUserQuery
import javax.inject.Inject

class NetworkClient @Inject constructor(private val apolloClient: ApolloClient, private val errorsHandler: NetworkErrorHandler) {

    suspend fun user(): Either<NetworkError, CurrentUserQuery.Data?> {
        return errorsHandler.withErrorHandler {
            apolloClient.query(CurrentUserQuery()).await()
        }
    }

    suspend fun login(email: String, password: String): Either<NetworkError, CreateSessionMutation.Data> {
        return errorsHandler.withErrorHandler {
            apolloClient.mutate(CreateSessionMutation(email, password)).await()
        }
    }

    suspend fun createUser(email: String, password: String): Either<NetworkError, CreateUserMutation.Data> {
        return errorsHandler.withErrorHandler {
            apolloClient.mutate(CreateUserMutation(email, password)).await()
        }
    }
}