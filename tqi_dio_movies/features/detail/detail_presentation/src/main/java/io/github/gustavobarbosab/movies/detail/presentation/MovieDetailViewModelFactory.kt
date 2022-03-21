package io.github.gustavobarbosab.movies.detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.gustavobarbosab.movies.favorites.domain.usecase.FavoriteMovieUseCase
import javax.inject.Inject

class MovieDetailViewModelFactory @Inject constructor(
    private val movieDetailUseCase: FavoriteMovieUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MovieDetailViewModel(movieDetailUseCase) as T
}