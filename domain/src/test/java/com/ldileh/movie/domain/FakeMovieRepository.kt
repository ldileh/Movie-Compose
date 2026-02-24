package com.ldileh.movie.domain

import com.ldileh.movie.domain.model.Movie
import com.ldileh.movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow

class FakeMovieRepository(
    initialMovies: List<Movie> = emptyList()
) : MovieRepository {

    private val movies = mutableListOf<Movie>().apply {
        addAll(initialMovies)
    }

    private val flow = MutableStateFlow(movies.toList())

    var shouldThrowError = false

    override fun getPopularMovies(): Flow<List<Movie>> = flow

    override suspend fun refreshPopularMovies() {
        if (shouldThrowError) throw RuntimeException("Network Error")

        movies.clear()
        movies.add(
            Movie(
                id = 100,
                title = "Refreshed Movie",
                overview = "Overview",
                posterUrl = null,
                releaseDate = null,
                rating = 9.0
            )
        )

        flow.value = movies.toList()
    }

    override suspend fun loadNextPage(page: Int) {
        if (shouldThrowError) throw RuntimeException("Network Error")

        val next = Movie(
            id = page,
            title = "Page $page Movie",
            overview = "Overview",
            posterUrl = null,
            releaseDate = null,
            rating = 7.5
        )

        movies.add(next)
        flow.value = movies.toList()
    }
}