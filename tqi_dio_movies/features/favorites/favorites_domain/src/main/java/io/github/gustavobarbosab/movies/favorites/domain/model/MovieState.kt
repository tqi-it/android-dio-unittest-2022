package io.github.gustavobarbosab.movies.favorites.domain.model

sealed class MovieState {
    object MovieLiked : MovieState()
    object MovieUnliked : MovieState()
}
