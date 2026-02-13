package com.ldileh.movie.domain.usecase

import com.ldileh.movie.domain.model.Movie
import com.ldileh.movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    operator fun invoke(page: Int = 1): Flow<List<Movie>>{
        return repository.getPopularMovies(page)
    }
}