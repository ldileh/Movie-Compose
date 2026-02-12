package com.ldileh.movie.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResponseDto(

    @param:Json(name = "page")
    val page: Int,

    @param:Json(name = "results")
    val results: List<MovieDto>,

    @param:Json(name = "total_pages")
    val totalPages: Int,

    @param:Json(name = "total_results")
    val totalResults: Int
)