package com.ldileh.movie.domain.repository

import com.ldileh.movie.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getPopularMovies(): Flow<List<Movie>>
    suspend fun refreshPopularMovies()
    suspend fun loadNextPage(page: Int)
}