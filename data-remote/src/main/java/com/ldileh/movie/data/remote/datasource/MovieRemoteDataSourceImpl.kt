package com.ldileh.movie.data.remote.datasource

import com.ldileh.movie.data.remote.api.MovieApi
import com.ldileh.movie.data.remote.dto.MovieDto
import com.ldileh.movie.data.remote.utils.exception.RemoteException
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

interface MovieRemoteDataSource {

    suspend fun getPopularMovies(page: Int): List<MovieDto>
}

class MovieRemoteDataSourceImpl @Inject constructor(
    private val api: MovieApi
): MovieRemoteDataSource {

    override suspend fun getPopularMovies(page: Int): List<MovieDto> {
        return try {
            api.getPopularMovies(page).results
        } catch (_: IOException) {
            throw RemoteException.Network
        } catch (e: HttpException) {
            throw RemoteException.Server(e.code())
        }
    }
}
