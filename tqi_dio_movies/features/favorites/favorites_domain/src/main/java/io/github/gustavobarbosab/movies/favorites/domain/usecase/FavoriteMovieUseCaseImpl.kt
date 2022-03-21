package io.github.gustavobarbosab.movies.favorites.domain.usecase

import io.github.gustavobarbosab.movies.favorites.domain.model.MovieFavorite
import io.github.gustavobarbosab.movies.favorites.domain.model.MovieState
import io.github.gustavobarbosab.movies.favorites.domain.repository.FavoriteMovieRepository
import io.gustavobarbosab.suspendresult.SuspendResult

class FavoriteMovieUseCaseImpl(
    private val repository: FavoriteMovieRepository
) : FavoriteMovieUseCase {

    override suspend fun fetchFavorites(): SuspendResult<List<MovieFavorite>> =
        repository.fetchFavorites()

    override suspend fun updateFavoriteMovie(movie: MovieFavorite): SuspendResult<MovieState> {
        val isMovieFavoriteResult = repository.isMovieFavorite(movie.id)

        if (isMovieFavoriteResult !is SuspendResult.Success<*>)
            return SuspendResult.UnknownError()

        val isMovieFavorite = isMovieFavoriteResult.data == true
        if (isMovieFavorite) {
            repository.unlikeMovie(movie.id)
            return SuspendResult.Success(MovieState.MovieUnliked)
        }

        repository.likeMovie(movie)
        return SuspendResult.Success(MovieState.MovieLiked)
    }

    override suspend fun unlikeMovie(id: Long): SuspendResult<Unit> =
        repository.unlikeMovie(id)

    override suspend fun isMovieFavorite(id: Long): SuspendResult<MovieState> {
        val result = repository.isMovieFavorite(id)
        return when {
            result is SuspendResult.Success<*> && result.data == true -> SuspendResult.Success(
                MovieState.MovieLiked
            )
            result is SuspendResult.Success<*> -> SuspendResult.Success(MovieState.MovieUnliked)
            else -> SuspendResult.UnknownError()
        }
    }
}