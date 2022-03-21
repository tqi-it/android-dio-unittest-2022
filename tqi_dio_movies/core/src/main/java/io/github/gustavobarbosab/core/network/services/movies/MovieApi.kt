package io.github.gustavobarbosab.core.network.services.movies

import io.github.gustavobarbosab.core.network.services.movies.dto.PopularMoviePagingResponse
import io.gustavobarbosab.suspendresult.SuspendResult
import retrofit2.http.GET

interface MovieApi {
    @GET("movie/popular")
    suspend fun getPopularMovies(): SuspendResult<PopularMoviePagingResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): SuspendResult<PopularMoviePagingResponse>

    @GET("movie/now_playing")
    suspend fun getPlayingNow(): SuspendResult<PopularMoviePagingResponse>

    @GET("movie/upcoming")
    suspend fun getLatestMovies(): SuspendResult<PopularMoviePagingResponse>
}