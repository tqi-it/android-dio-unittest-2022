package io.github.gustavobarbosab.movies.detail

import io.github.gustavobarbosab.movies.navigation.arguments.detail.MovieDetailArgument

object MovieDetailMocker {

    fun createMovieDetailArgument() = MovieDetailArgument(
        MOVIE_ID,
        "A volta dos que não foram",
        "Enchente em auto mar",
        "sua imagem aqui",
        "seu poster aqui"
    )

    const val MOVIE_ID = 1L
}