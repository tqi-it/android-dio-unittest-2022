package io.github.gustavobarbosab.movies.detail.model

import io.github.gustavobarbosab.core.contracts.Mapper
import io.github.gustavobarbosab.movies.favorites.domain.model.MovieFavorite
import io.github.gustavobarbosab.movies.navigation.arguments.detail.MovieDetailArgument

class DetailPresentationMapper : Mapper<MovieDetailArgument, DetailModel> {
    override fun map(input: MovieDetailArgument) = DetailModel(
        input.id,
        input.poster,
        input.name,
        input.description,
        input.imageUrl
    )

    fun map(input: DetailModel) = MovieFavorite(
        input.id,
        input.title,
        input.description,
        input.imageUrl,
        input.poster
    )
}