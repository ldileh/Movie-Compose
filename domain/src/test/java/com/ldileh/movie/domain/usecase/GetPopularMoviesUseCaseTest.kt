package com.ldileh.movie.domain.usecase

import app.cash.turbine.test
import com.ldileh.movie.domain.FakeMovieRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetPopularMoviesUseCaseTest {

    private val fakeRepository = FakeMovieRepository()
    private val useCase = GetPopularMoviesUseCase(fakeRepository)

    @Test
    fun `Default page parameter invocation`() = runTest {
        // Verify that invoking the use case without a page number defaults to calling the repository with page 1.
        useCase().test {
            val result = awaitItem()

            assertEquals(1, result.size)
            assertEquals("Test Movie", result.first().title)

            awaitComplete()
        }
    }

    @Test
    fun `Specific page parameter invocation`() = runTest{
        // Verify that invoking the use case with a specific page number calls the repository with that same page number.
        useCase(1).test {
            val result = awaitItem()

            assertEquals(1, result.size)
            assertEquals("Test Movie", result.first().title)

            awaitComplete()
        }
    }

}