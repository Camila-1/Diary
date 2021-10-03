package com.pchpsky.diary.datasourse.network

import android.content.Context
import com.apollographql.apollo.ApolloClient
import com.pchpsky.diary.datasourse.localstorage.DataStoreManager
import com.pchpsky.diary.exceptions.handlers.NetworkErrorHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideApolloClient(@ApplicationContext context: Context, dataStoreManager: DataStoreManager): ApolloClient {
        var token: String? = null
        CoroutineScope(Dispatchers.IO).launch {
            token = dataStoreManager.userToken().stateIn(CoroutineScope(Dispatchers.IO)).value
        }

        val okHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(AuthorizationInterceptor(context, token))
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