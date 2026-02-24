package com.ldileh.movie.data.repository

import androidx.room.withTransaction
import com.ldileh.movie.data.local.MovieDatabase
import com.ldileh.movie.data.local.dao.MovieDao
import com.ldileh.movie.data.mapper.toDomain
import com.ldileh.movie.data.mapper.toEntity
import com.ldileh.movie.data.remote.datasource.MovieRemoteDataSource
import com.ldileh.movie.domain.model.Movie
import com.ldileh.movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val database: MovieDatabase,
    private val dao: MovieDao
) : MovieRepository {

    override fun getPopularMovies(): Flow<List<Movie>> {
        return dao.observeMovies()
            .map { entities ->
                entities.map { it.toDomain() }
            }
    }

    /**
     * Refresh data movies with only get data of movie at page 1 from remote and save it to local storage
     */
    override suspend fun refreshPopularMovies() {
        val movies = remoteDataSource.getPopularMovies(1)

        database.withTransaction {
            dao.clearAll()
            dao.insertAll(movies.map { it.toEntity() })
        }
    }

    override suspend fun loadNextPage(page: Int) {
        val movies = remoteDataSource.getPopularMovies(page)
        dao.insertAll(movies.map { it.toEntity() })
    }
}