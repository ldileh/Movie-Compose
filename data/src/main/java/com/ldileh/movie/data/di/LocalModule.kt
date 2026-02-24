package com.ldileh.movie.data.di

import android.content.Context
import com.ldileh.movie.data.local.dao.MovieDao
import com.ldileh.movie.data.local.MovieDatabase
import com.ldileh.movie.data.local.DatabaseFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): MovieDatabase {
        return DatabaseFactory.create(context)
    }

    @Provides
    fun provideMovieDao(
        database: MovieDatabase
    ): MovieDao {
        return database.movieDao()
    }
}