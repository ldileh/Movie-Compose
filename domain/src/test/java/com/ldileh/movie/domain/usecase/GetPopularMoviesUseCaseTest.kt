package com.ldileh.movie.domain.usecase

import app.cash.turbine.test
import com.ldileh.movie.domain.FakeMovieRepository
import com.ldileh.movie.domain.model.Movie
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetPopularMoviesUseCaseTest {

    private lateinit var fakeRepository: FakeMovieRepository
    private lateinit var useCase: GetPopularMoviesUseCase

    @Before
    fun setUp() {
        val initialMovies = listOf(
            Movie(1, "Initial", "Overview", null, null, 8.0)
        )
        fakeRepository = FakeMovieRepository(initialMovies)
        useCase = GetPopularMoviesUseCase(fakeRepository)
    }

    @Test
    fun `Default page parameter invocation`() = runTest {
        useCase().test {
            val result = awaitItem()

            assertEquals(1, result.size)
            assertEquals("Initial", result.first().title)
        }
    }

    @Test
    fun `Should replace data`() = runTest {
        useCase().test {
            assertEquals("Initial", awaitItem().first().title)

            fakeRepository.refreshPopularMovies()
            val result = awaitItem()
            assertEquals(1, result.size)
            assertEquals("Refreshed Movie", result.first().title)
        }
    }

    @Test
    fun `Should append data`() = runTest {
        useCase().test {
            assertEquals("Initial", awaitItem().first().title)

            fakeRepository.refreshPopularMovies()
            val refreshResult = awaitItem()
            assertEquals(1, refreshResult.size)
            assertEquals("Refreshed Movie", refreshResult.first().title)

            fakeRepository.loadNextPage(2)
            val nextPage = awaitItem()
            assertEquals(2, nextPage.size)
        }
    }

}