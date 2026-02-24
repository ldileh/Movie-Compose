package com.ldileh.movie.data.repository

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ldileh.movie.data.FakeMovieRemoteDataSource
import com.ldileh.movie.data.local.DatabaseFactory
import com.ldileh.movie.data.local.MovieDatabase
import com.ldileh.movie.data.local.dao.MovieDao
import com.ldileh.movie.data.remote.dto.MovieDto
import com.ldileh.movie.domain.repository.MovieRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class MovieRepositoryTest {

    private lateinit var database: MovieDatabase
    private lateinit var dao: MovieDao
    private lateinit var repository: MovieRepository

    @Before
    fun setUp(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = DatabaseFactory.createTest(context)
        dao = database.movieDao()
    }

    @After
    fun tearDown(){
        database.close()
    }

    @Test
    fun refreshPopularMovies_shouldReplaceLocalData() = runTest {
        val remoteMovies = listOf(
            MovieDto(1, "Movie 1", "Overview", null, null, 8.0)
        )

        val fakeRemote = FakeMovieRemoteDataSource(remoteMovies)

        repository = MovieRepositoryImpl(
            remoteDataSource = fakeRemote,
            database = database,
            dao = dao
        )

        repository.refreshPopularMovies()

        val result = dao.observeMovies().first()

        assertEquals(1, result.size)
        assertEquals("Movie 1", result.first().title)
    }
}