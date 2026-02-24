package com.ldileh.movie.data

import com.ldileh.movie.data.remote.datasource.MovieRemoteDataSource
import com.ldileh.movie.data.remote.dto.MovieDto

class FakeMovieRemoteDataSource(
    private val movies: List<MovieDto>
): MovieRemoteDataSource {

    override suspend fun getPopularMovies(page: Int): List<MovieDto> {
        return movies
    }
}