package io.github.gustavobarbosab.movies.data.repository

import io.github.gustavobarbosab.movies.data.datasources.local.ShowCaseLocalDataSource
import io.github.gustavobarbosab.movies.data.datasources.remote.ShowCaseRemoteDataSource
import io.github.gustavobarbosab.showcase.model.MovieShowCase
import io.github.gustavobarbosab.showcase.repository.ShowCaseRepository
import io.gustavobarbosab.suspendresult.SuspendResult
import javax.inject.Inject

class ShowCaseRepositoryImpl @Inject constructor(
    private val localDataSource: ShowCaseLocalDataSource,
    private val remoteDataSource: ShowCaseRemoteDataSource
) : ShowCaseRepository {

    override suspend fun getPopularMovies(): SuspendResult<List<MovieShowCase>> =
        remoteDataSource.getPopularMovies()

    override suspend fun getTopRatedMovies(): SuspendResult<List<MovieShowCase>> =
        remoteDataSource.getTopRatedMovies()

    override suspend fun getPlayingNow(): SuspendResult<List<MovieShowCase>> =
        remoteDataSource.getPlayingNow()

    override suspend fun getLatestMovies(): SuspendResult<List<MovieShowCase>> =
        remoteDataSource.getLatestMovies()
}