package io.github.gustavobarbosab.movies.data.datasources.remote

import io.github.gustavobarbosab.core.network.services.movies.MovieApi
import io.github.gustavobarbosab.movies.data.repository.ShowCaseRepositoryMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ShowCaseRemoteDataSource @Inject constructor(
    private val movieApi: MovieApi,
    private val mapper: ShowCaseRepositoryMapper
) {

    suspend fun getPopularMovies() = withContext(Dispatchers.IO) {
        return@withContext movieApi.getPopularMovies().map(mapper::map)
    }

    suspend fun getTopRatedMovies() = withContext(Dispatchers.IO) {
        return@withContext movieApi.getTopRatedMovies().map(mapper::map)
    }

    suspend fun getPlayingNow() = withContext(Dispatchers.IO) {
        return@withContext movieApi.getPlayingNow().map(mapper::map)
    }

    suspend fun getLatestMovies() = withContext(Dispatchers.IO) {
        return@withContext movieApi.getLatestMovies().map(mapper::map)
    }
}