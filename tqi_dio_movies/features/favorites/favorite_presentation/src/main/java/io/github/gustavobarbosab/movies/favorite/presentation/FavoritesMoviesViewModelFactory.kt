package io.github.gustavobarbosab.movies.favorite.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.gustavobarbosab.core.di.scope.FeatureScope
import io.github.gustavobarbosab.movies.favorites.domain.usecase.FavoriteMovieUseCase
import javax.inject.Inject

@FeatureScope
class FavoritesMoviesViewModelFactory @Inject constructor(
    private val favoriteMovieUseCase: FavoriteMovieUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        FavoritesMoviesViewModel(favoriteMovieUseCase) as T
}