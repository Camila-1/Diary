package com.pchpsky.diary.data.network

import com.apollographql.apollo.ApolloClient
import com.pchpsky.diary.data.localstorage.TokenStore
import com.pchpsky.diary.data.network.exceptions.handlers.NetworkErrorHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    fun provideApolloClient(): ApolloClient {

        val okHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(AuthorizationInterceptor(TokenStore()))
            .connectTimeout(30L, TimeUnit.SECONDS)
//            .readTimeout(30L, TimeUnit.SECONDS)
//            .writeTimeout(30L, TimeUnit.SECONDS)
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