package com.ldileh.movie.data.repository

import com.ldileh.movie.data.remote.datasource.MovieRemoteDataSource
import com.ldileh.movie.domain.model.Movie
import com.ldileh.movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource
): MovieRepository {

    override fun getMovies(page: Int): Flow<List<Movie>> = flow {
        emit(remoteDataSource.getPopularMovies(page))
    }
}