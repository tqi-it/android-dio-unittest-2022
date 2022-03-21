package io.github.gustavobarbosab.movies.favorites.data.datasource

import io.github.gustavobarbosab.core.database.MovieDao
import io.github.gustavobarbosab.core.database.dto.MovieDto
import io.github.gustavobarbosab.movies.favorites.data.mapper.FavoriteMovieDataMapper
import io.github.gustavobarbosab.movies.favorites.domain.model.MovieFavorite
import io.gustavobarbosab.suspendresult.SuspendResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoriteMovieLocalDataSource @Inject constructor(
    private val movieDao: MovieDao
) {
    private val mapper = FavoriteMovieDataMapper()

    suspend fun fetchFavoritesMovies(): SuspendResult<List<MovieFavorite>> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val favorites = movieDao.getFavorites().map(mapper::map)
                SuspendResult.Success(favorites)
            } catch (e: Exception) {
                SuspendResult.UnknownError(e)
            }
        }

    suspend fun isMovieLiked(id: Long): SuspendResult<Boolean> = withContext(Dispatchers.IO) {
        return@withContext try {
            val favoriteMovie = movieDao.getFavorite(id)
            val isMovieLiked = favoriteMovie != null
            SuspendResult.Success(isMovieLiked)
        } catch (e: Exception) {
            SuspendResult.UnknownError(e)
        }
    }

    suspend fun likeMovie(movie: MovieFavorite): SuspendResult<Unit> = withContext(Dispatchers.IO) {
        return@withContext try {
            val movieDto: MovieDto = mapper.map(movie, favorite = true)
            movieDao.insertMovie(movieDto)
            SuspendResult.Success(Unit)
        } catch (e: Exception) {
            SuspendResult.UnknownError(e)
        }
    }

    suspend fun unlikeMovie(id: Long): SuspendResult<Unit> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                movieDao.unlikeMovie(id)
                SuspendResult.Success(Unit)
            } catch (e: Exception) {
                SuspendResult.UnknownError(e)
            }
        }
}