package com.ldileh.movie.data.di

import com.ldileh.movie.data.remote.api.MovieApi
import com.ldileh.movie.data.remote.config.RemoteConfig
import com.ldileh.movie.data.remote.utils.OkHttpFactory
import com.ldileh.movie.data.remote.utils.RetrofitFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(remoteConfig: RemoteConfig): OkHttpClient =
        OkHttpFactory.create(remoteConfig)

    @Provides
    @Singleton
    fun provideRetrofit(
        remoteConfig: RemoteConfig,
        moshi: Moshi,
        httpClient: OkHttpClient
    ): Retrofit {
        return RetrofitFactory.create(config = remoteConfig, client = httpClient, moshi = moshi)
    }

    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit): MovieApi = retrofit.create(MovieApi::class.java)

}