package com.ldileh.movie.data.remote.datasource

import com.ldileh.movie.data.remote.api.MovieApi
import com.ldileh.movie.data.remote.mapper.toDomain
import com.ldileh.movie.data.remote.utils.exception.RemoteException
import com.ldileh.movie.domain.model.Movie
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val api: MovieApi
) {

    suspend fun getPopularMovies(page: Int): List<Movie> {
        return try {
            api.getPopularMovies(page).results.map { it.toDomain() }
        } catch (e: IOException) {
            throw RemoteException.Network
        } catch (e: HttpException) {
            throw RemoteException.Server(e.code())
        }
    }
}
