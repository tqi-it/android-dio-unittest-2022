package io.github.gustavobarbosab.movies.favorites.domain

import io.github.gustavobarbosab.movies.favorites.domain.model.MovieFavorite

object FavoriteMovieMocker {

    fun createMovie() = MovieFavorite(
        1L,
        "A volta dos que não foram",
        "Enchente em auto mar",
        "sua imagem aqui",
        "seu poster aqui"
    )
}