package io.github.gustavobarbosab.detail.model

data class MovieDetailDomain(
    val id: Long,
    val name: String,
    val description: String,
    val imageUrl: String,
    val poster: String
)