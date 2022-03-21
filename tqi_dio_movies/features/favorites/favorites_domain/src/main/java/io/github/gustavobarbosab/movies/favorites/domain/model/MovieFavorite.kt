package io.github.gustavobarbosab.movies.favorites.domain.model

class MovieFavorite(
    val id: Long,
    val name: String,
    val description: String,
    val imageUrl: String,
    val poster: String,
    val savedDate: String = ""
)