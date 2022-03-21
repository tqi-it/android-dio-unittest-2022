package io.github.gustavobarbosab.movies.showcase.presentation.model

data class ShowCaseModel(
    val id: Long,
    val name: String,
    val description: String,
    val imageUrl: String,
    val posterUrl: String,
    val isFavorite: Boolean
)