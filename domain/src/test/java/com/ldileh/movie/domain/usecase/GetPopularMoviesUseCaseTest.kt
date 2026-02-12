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

    @Test
    fun `Repository success with a list of movies`() {
        // Given the repository's getMovies function returns a Flow with a list of movies,
        // When the use case is invoked,
        // Then the output Flow should emit the same list of movies.
        // TODO implement test
    }

    @Test
    fun `Repository success with an empty list of movies`() {
        // Given the repository's getMovies function returns a Flow with an empty list,
        // When the use case is invoked,
        // Then the output Flow should emit an empty list.
        // TODO implement test
    }

    @Test
    fun `Repository failure with an exception`() {
        // Given the repository's getMovies function throws an exception,
        // When the use case is invoked,
        // Then the output Flow should emit an error.
        // TODO implement test
    }

    @Test
    fun `Large page number`() {
        // Test the behavior when a very large integer is passed as the page number, for example, Int.MAX_VALUE.
        // This checks for any potential overflow or unexpected behavior in the repository layer. [4, 7]
        // TODO implement test
    }

    @Test
    fun `Negative page number`() {
        // Test the behavior when a negative integer is passed as the page number.
        // The repository should handle this gracefully, likely by returning an error or an empty list. [8]
        // TODO implement test
    }

    @Test
    fun `Zero as page number`() {
        // Test the behavior when the page number is zero.
        // Similar to a negative page number, the repository should handle this case gracefully.
        // TODO implement test
    }

    @Test
    fun `Flow cancellation`() {
        // Verify that if the collection of the Flow is cancelled, the underlying call to the repository is also cancelled.
        // TODO implement test
    }

    @Test
    fun `Multiple emissions from repository`() {
        // If the repository's Flow can emit multiple lists of movies over time (e.g., from a local cache then a network source),
        // verify that the use case's Flow correctly emits all of these lists to the collector. [3]
        // TODO implement test
    }

    @Test
    fun `Concurrent invocations`() {
        // Test invoking the use case multiple times concurrently with different page numbers to ensure thread safety and correct data streams for each invocation.
        // TODO implement test
    }

}