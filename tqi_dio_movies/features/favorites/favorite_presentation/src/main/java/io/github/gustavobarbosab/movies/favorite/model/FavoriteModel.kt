package io.github.gustavobarbosab.movies.favorite.model

data class FavoriteModel(
    val id: Long,
    val imageUrl: String,
    val movieName: String,
    val savedDate: String
)