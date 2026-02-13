package com.ldileh.movie.domain

import com.ldileh.movie.domain.model.Movie
import com.ldileh.movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeMovieRepository: MovieRepository {

    override fun getPopularMovies(page: Int): Flow<List<Movie>> = flow {
        emit(
            listOf(
                Movie(
                    id = 1,
                    title = "Test Movie",
                    overview = "Overview",
                    posterUrl = null,
                    releaseDate = null,
                    rating = 8.0
                )
            )
        )
    }
}