package com.pchpsky.diary.datasourse.network

import android.content.Context
import com.apollographql.apollo.ApolloClient
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideApolloClient(context: Context): ApolloClient {
        return ApolloClient.builder()
            .serverUrl("https://pchpsky-diary.herokuapp.com/graph")
            .okHttpClient(
                OkHttpClient
                    .Builder()
                    .addInterceptor(AuthorizationInterceptor(context))
                    .build()
            )
            .build()
    }

    @Provides
    fun provideNetworkClient(apolloClient: ApolloClient) = NetworkClient(apolloClient)
}