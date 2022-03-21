package io.github.gustavobarbosab.movies.data.repository

import io.github.gustavobarbosab.core.config.MoovieConfig
import io.github.gustavobarbosab.core.network.services.movies.dto.PopularMovieResponse
import io.github.gustavobarbosab.core.contracts.Mapper
import io.github.gustavobarbosab.core.network.services.movies.dto.PopularMoviePagingResponse
import io.github.gustavobarbosab.showcase.model.MovieShowCase
import javax.inject.Inject

class ShowCaseRepositoryMapper @Inject constructor(
    private val configWrapper: MoovieConfig
) : Mapper<PopularMoviePagingResponse?, List<MovieShowCase>> {

    override fun map(input: PopularMoviePagingResponse?): List<MovieShowCase>  =
        input?.results?.map(this::mapToShowCase) ?: emptyList()

    private fun mapToShowCase(input: PopularMovieResponse) =
        MovieShowCase(
            input.id,
            input.title,
            input.overview,
            configWrapper.formatImageUrl(input.posterPath),
            configWrapper.formatImageUrl(input.backdropPath),
            false
        )
}