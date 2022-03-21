package io.github.gustavobarbosab.core.network.services.movies.dto

import com.squareup.moshi.Json

data class PopularMoviePagingResponse(
    @field:Json(name = "page") val page: Int,
    @field:Json(name = "results") val results: List<PopularMovieResponse>,
    @field:Json(name = "total_pages") val totalPages: Int,
    @field:Json(name = "total_results") val totalResults: Int
)