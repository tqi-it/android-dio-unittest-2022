package io.github.gustavobarbosab.showcase.model

data class MovieShowCase(
    val id: Long,
    val name: String,
    val description: String,
    val imageUrl: String,
    val posterUrl: String,
    val isFavorite: Boolean
)