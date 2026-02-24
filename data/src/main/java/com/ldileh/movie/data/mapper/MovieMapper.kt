package com.ldileh.movie.data.mapper

import com.ldileh.movie.data.local.entity.MovieEntity
import com.ldileh.movie.data.remote.dto.MovieDto
import com.ldileh.movie.domain.model.Movie

private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

fun MovieDto.toEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        title = title,
        overview = overview,
        posterUrl = posterPath?.let { IMAGE_BASE_URL + it },
        releaseDate = releaseDate,
        rating = voteAverage
    )
}

fun MovieEntity.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        posterUrl = posterUrl,
        releaseDate = releaseDate,
        rating = rating
    )
}

