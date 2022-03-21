package io.github.gustavobarbosab.movies.detail.model

sealed class DetailsButtonType {
    object Filled: DetailsButtonType()
    object Outline: DetailsButtonType()
}