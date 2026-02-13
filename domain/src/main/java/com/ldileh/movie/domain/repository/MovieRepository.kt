package com.ldileh.movie.domain.repository

import com.ldileh.movie.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getPopularMovies(page: Int): Flow<List<Movie>>
}