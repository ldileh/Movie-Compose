package com.ldileh.movie.data.local.dao

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.ldileh.movie.data.local.DatabaseFactory
import com.ldileh.movie.data.local.MovieDatabase
import com.ldileh.movie.data.local.entity.MovieEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class MovieDaoTest {

    private lateinit var database: MovieDatabase
    private lateinit var dao: MovieDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = DatabaseFactory.createTest(context)
        dao = database.movieDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertMovies_shouldEmitMovies() = runTest {
        val movies = listOf(
            MovieEntity(
                id = 1,
                title = "Test",
                overview = "Overview",
                posterUrl = null,
                releaseDate = null,
                rating = 8.0
            )
        )

        dao.insertAll(movies)
        dao.observeMovies().test {
            val result = awaitItem()
            assertEquals(1, result.size)
            assertEquals("Test", result.first().title)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun insertMovies_loadNextPage() = runTest {
        val movies = listOf(
            MovieEntity(
                id = 1,
                title = "Test",
                overview = "Overview",
                posterUrl = null,
                releaseDate = null,
                rating = 8.0
            ),
            MovieEntity(
                id = 2,
                title = "Test 2",
                overview = "Overview",
                posterUrl = null,
                releaseDate = null,
                rating = 8.0
            )
        )

        val nextPageMovie = listOf(
            MovieEntity(
                id = 3,
                title = "Test 3",
                overview = "Overview",
                posterUrl = null,
                releaseDate = null,
                rating = 8.0
            )
        )

        dao.observeMovies().test {
            // first case
            assertTrue(awaitItem().isEmpty())

            // case first load
            dao.insertAll(movies)
            assertEquals(2, awaitItem().size)

            // case two load next page
            dao.insertAll(nextPageMovie)
            assertEquals(3, awaitItem().size)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun insertMovies_refreshPage() = runTest {
        val movies = listOf(
            MovieEntity(
                id = 1,
                title = "Test",
                overview = "Overview",
                posterUrl = null,
                releaseDate = null,
                rating = 8.0
            ),
            MovieEntity(
                id = 2,
                title = "Test 2",
                overview = "Overview",
                posterUrl = null,
                releaseDate = null,
                rating = 8.0
            )
        )

        val firstPageMovie = listOf(
            MovieEntity(
                id = 1,
                title = "Test",
                overview = "Overview",
                posterUrl = null,
                releaseDate = null,
                rating = 8.0
            )
        )

        dao.observeMovies().test {
            // first case
            assertTrue(awaitItem().isEmpty())

            // insert the item case
            dao.insertAll(movies)
            assertEquals(2, awaitItem().size)

            // clear all items
            dao.clearAll()
            assertTrue(awaitItem().isEmpty())

            // then refresh the data
            dao.insertAll(firstPageMovie)
            assertEquals(1, awaitItem().size)

            cancelAndIgnoreRemainingEvents()
        }
    }
}