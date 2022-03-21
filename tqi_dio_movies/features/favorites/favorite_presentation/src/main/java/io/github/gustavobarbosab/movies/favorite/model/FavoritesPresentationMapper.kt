package io.github.gustavobarbosab.movies.favorite.model

import io.github.gustavobarbosab.movies.favorites.domain.model.MovieFavorite

class FavoritesPresentationMapper {

    fun map(movie: MovieFavorite) = FavoriteModel(
        movie.id,
        movie.poster,
        movie.name,
        movie.savedDate
    )
}