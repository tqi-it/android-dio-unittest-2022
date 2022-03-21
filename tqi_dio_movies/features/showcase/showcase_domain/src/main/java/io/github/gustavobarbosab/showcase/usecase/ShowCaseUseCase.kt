package io.github.gustavobarbosab.showcase.usecase

import io.github.gustavobarbosab.showcase.model.MovieShowCase
import io.gustavobarbosab.suspendresult.SuspendResult

interface ShowCaseUseCase {
    suspend fun getPopularMovies(): SuspendResult<List<MovieShowCase>>
    suspend fun getTopRatedMovies(): SuspendResult<List<MovieShowCase>>
    suspend fun getPlayingNow(): SuspendResult<List<MovieShowCase>>
    suspend fun getLatestMovies(): SuspendResult<List<MovieShowCase>>
}