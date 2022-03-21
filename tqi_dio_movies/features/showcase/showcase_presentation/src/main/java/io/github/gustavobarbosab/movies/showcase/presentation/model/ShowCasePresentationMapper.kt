package io.github.gustavobarbosab.movies.showcase.presentation.model

import io.github.gustavobarbosab.detail.model.MovieDetailDomain
import io.github.gustavobarbosab.showcase.model.MovieShowCase

class ShowCasePresentationMapper {

    fun map(model: ShowCaseModel) = MovieDetailDomain(
        model.id,
        model.name,
        model.description,
        model.imageUrl,
        model.posterUrl,
    )

    fun map(showCase: MovieShowCase) = ShowCaseModel(
        showCase.id,
        showCase.name,
        showCase.description,
        showCase.imageUrl,
        showCase.posterUrl,
        showCase.isFavorite
    )
}