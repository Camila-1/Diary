package com.pchpsky.diary.datasourse.network

import android.content.Context
import com.apollographql.apollo.ApolloClient
import com.pchpsky.diary.DiaryApplication
import com.pchpsky.diary.di.ApplicationComponent
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.migration.DisableInstallInCheck
import okhttp3.OkHttpClient
import javax.inject.Inject
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

    @Singleton
    @Provides
    fun provideNetworkClient(apolloClient: ApolloClient) = NetworkClient(apolloClient)
}