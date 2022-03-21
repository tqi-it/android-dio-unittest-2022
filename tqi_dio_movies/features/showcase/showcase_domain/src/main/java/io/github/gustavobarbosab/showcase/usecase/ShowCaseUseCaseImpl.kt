package io.github.gustavobarbosab.showcase.usecase

import io.github.gustavobarbosab.showcase.model.MovieShowCase
import io.github.gustavobarbosab.showcase.repository.ShowCaseRepository
import io.gustavobarbosab.suspendresult.SuspendResult

class ShowCaseUseCaseImpl(private val movieRepository: ShowCaseRepository) : ShowCaseUseCase {

    override suspend fun getPopularMovies(): SuspendResult<List<MovieShowCase>> =
        movieRepository.getPopularMovies()

    override suspend fun getTopRatedMovies(): SuspendResult<List<MovieShowCase>> =
        movieRepository.getTopRatedMovies()

    override suspend fun getPlayingNow(): SuspendResult<List<MovieShowCase>> =
        movieRepository.getPlayingNow()

    override suspend fun getLatestMovies(): SuspendResult<List<MovieShowCase>> =
        movieRepository.getLatestMovies()
}