package com.pchpsky.diary.datasource.network

import com.apollographql.apollo.ApolloClient
import com.pchpsky.diary.datasource.localstorage.TokenStore
import com.pchpsky.diary.exceptions.handlers.NetworkErrorHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    fun provideApolloClient(): ApolloClient {

        val okHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(AuthorizationInterceptor(TokenStore()))
            .build()
        return ApolloClient.builder()
            .serverUrl("https://pchpsky-diary.herokuapp.com/graph")
            .okHttpClient(okHttpClient)
            .build()
    }

    @Provides
    fun provideErrorHandler() = NetworkErrorHandler()

    @Provides
    fun provideNetworkClient(apolloClient: ApolloClient, errorHandler: NetworkErrorHandler) = NetworkClient(apolloClient, errorHandler)
}