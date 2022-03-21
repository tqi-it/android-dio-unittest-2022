package io.github.gustavobarbosab.movies.favorites.domain.repository

import io.github.gustavobarbosab.movies.favorites.domain.model.MovieFavorite
import io.gustavobarbosab.suspendresult.SuspendResult

interface FavoriteMovieRepository {
    suspend fun isMovieFavorite(id: Long): SuspendResult<Boolean>
    suspend fun likeMovie(movie: MovieFavorite): SuspendResult<Unit>
    suspend fun unlikeMovie(movie: Long): SuspendResult<Unit>
    suspend fun fetchFavorites(): SuspendResult<List<MovieFavorite>>
}