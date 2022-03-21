package io.github.gustavobarbosab.movies.navigation.arguments.detail

import io.github.gustavobarbosab.core.contracts.Mapper
import io.github.gustavobarbosab.detail.model.MovieDetailDomain

class MovieDetailMapper : Mapper<MovieDetailDomain, MovieDetailArgument> {
    override fun map(input: MovieDetailDomain) = MovieDetailArgument(
        input.id,
        input.name,
        input.description,
        input.imageUrl,
        input.poster
    )
}