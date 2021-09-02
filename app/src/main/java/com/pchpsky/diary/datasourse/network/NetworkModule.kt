package com.pchpsky.diary.datasourse.network

import android.content.Context
import com.apollographql.apollo.ApolloClient
import com.pchpsky.diary.exceptions.handlers.NetworkErrorHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideApolloClient(@ApplicationContext context: Context): ApolloClient {
        val okHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(AuthorizationInterceptor(context))
            .build()
        return ApolloClient.builder()
            .serverUrl("https://pchpsky-diary.herokuapp.com/graph")
            .okHttpClient(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideErrorHandler() = NetworkErrorHandler()

    @Singleton
    @Provides
    fun provideNetworkClient(apolloClient: ApolloClient, errorHandler: NetworkErrorHandler) = NetworkClient(apolloClient, errorHandler)
}