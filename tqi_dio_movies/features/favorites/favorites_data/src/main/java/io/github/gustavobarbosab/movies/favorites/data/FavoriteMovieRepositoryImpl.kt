package io.github.gustavobarbosab.movies.favorites.data

import io.github.gustavobarbosab.movies.favorites.data.datasource.FavoriteMovieLocalDataSource
import io.github.gustavobarbosab.movies.favorites.data.datasource.FavoriteMovieRemoteDataSource
import io.github.gustavobarbosab.movies.favorites.domain.model.MovieFavorite
import io.github.gustavobarbosab.movies.favorites.domain.repository.FavoriteMovieRepository
import io.gustavobarbosab.suspendresult.SuspendResult
import javax.inject.Inject

class FavoriteMovieRepositoryImpl @Inject constructor(
    private val localDataSource: FavoriteMovieLocalDataSource,
    val remoteDataSource: FavoriteMovieRemoteDataSource
) : FavoriteMovieRepository {

    override suspend fun fetchFavorites() = localDataSource.fetchFavoritesMovies()

    override suspend fun isMovieFavorite(id: Long): SuspendResult<Boolean> =
        localDataSource.isMovieLiked(id)

    override suspend fun likeMovie(movie: MovieFavorite): SuspendResult<Unit> =
        localDataSource.likeMovie(movie)

    override suspend fun unlikeMovie(id: Long): SuspendResult<Unit> =
        localDataSource.unlikeMovie(id)
}