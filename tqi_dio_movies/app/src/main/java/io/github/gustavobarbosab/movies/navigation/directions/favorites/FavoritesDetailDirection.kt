package io.github.gustavobarbosab.movies.navigation.directions.favorites

import androidx.navigation.NavDirections
import io.github.gustavobarbosab.detail.model.MovieDetailDomain
import io.github.gustavobarbosab.movies.favorite.presentation.FavoriteMoviesFragmentDirections
import io.github.gustavobarbosab.movies.navigation.arguments.detail.MovieDetailMapper
import io.github.gustavobarbosab.movies.navigation.directions.DirectionAdapter

class FavoritesDetailDirection(val detail: MovieDetailDomain) : DirectionAdapter {

    private var mapper = MovieDetailMapper()

    override fun createDirection(): NavDirections {
        val argument = mapper.map(detail)
        return FavoriteMoviesFragmentDirections.actionDetailFragment(argument)
    }
}