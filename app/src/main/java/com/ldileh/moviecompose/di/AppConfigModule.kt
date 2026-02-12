package com.ldileh.moviecompose.di

import com.ldileh.movie.data.remote.config.RemoteConfig
import com.ldileh.moviecompose.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppConfigModule {

    @Provides
    @BaseUrl
    fun provideBaseUrl(): String = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun provideRemoteConfig(@BaseUrl baseUrl: String): RemoteConfig {
        return RemoteConfig(
            baseUrl = baseUrl,
            apiKey = BuildConfig.API_KEY,
            enableLogging = true,
            connectTimeoutSeconds = 30,
            readTimeoutSeconds = 30
        )
    }
}
