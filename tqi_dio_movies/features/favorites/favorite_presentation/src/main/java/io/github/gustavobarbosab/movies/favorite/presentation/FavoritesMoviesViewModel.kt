package io.github.gustavobarbosab.movies.favorite.presentation

import androidx.lifecycle.viewModelScope
import io.github.gustavobarbosab.commons.extension.launchMain
import io.github.gustavobarbosab.commons.ui.base.BaseViewModel
import io.github.gustavobarbosab.movies.favorite.model.FavoriteModel
import io.github.gustavobarbosab.movies.favorite.model.FavoritesPresentationMapper
import io.github.gustavobarbosab.movies.favorite.presentation.FavoritesMoviesState.*
import io.github.gustavobarbosab.movies.favorite.presentation.FavoritesMoviesState.LayoutState.*
import io.github.gustavobarbosab.movies.favorites.domain.model.MovieFavorite
import io.github.gustavobarbosab.movies.favorites.domain.usecase.FavoriteMovieUseCase
import io.gustavobarbosab.suspendresult.CoroutineResultHandler
import javax.inject.Inject

class FavoritesMoviesViewModel @Inject constructor(
    private val useCase: FavoriteMovieUseCase
) : BaseViewModel<FavoritesMoviesState>(), CoroutineResultHandler {

    private val mapper = FavoritesPresentationMapper()
    private var favoritesList = mutableListOf<FavoriteModel>()

    override val state: FavoritesMoviesState = FavoritesMoviesState()

    fun fetchFavorites() {
        viewModelScope.launchMain {
            state.layout.value = HideAll
            state.actions.value = ViewAction.ShowLoading
            handleResult(
                useCase.fetchFavorites(),
                this@FavoritesMoviesViewModel::fetchFavoritesSuccess,
                this@FavoritesMoviesViewModel::fetchFavoritesFailure
            )
            state.actions.value = ViewAction.HideLoading
        }
    }

    private fun fetchFavoritesSuccess(result: List<MovieFavorite>?) {
        val favorites: List<MovieFavorite> = result ?: emptyList()
        favoritesList = favorites.map(mapper::map).toMutableList()

        if (favoritesList.isNullOrEmpty()) {
            state.layout.value = ShowEmptyState
            return
        }

        state.layout.value = ShowRecyclerView(favoritesList)
    }

    private fun fetchFavoritesFailure() {
        state.layout.value = ShowTryAgain
    }

    fun unlikeMovie(model: FavoriteModel, position: Int) {
        viewModelScope.launchMain {
            state.actions.value = ViewAction.ShowLoading
            handleResult(
                useCase.unlikeMovie(model.id),
                { unlikeMovieSuccess(position) },
                this@FavoritesMoviesViewModel::unlikeMovieFailure
            )
            state.actions.value = ViewAction.HideLoading
        }
    }

    private fun unlikeMovieSuccess(position: Int) {
        state.actions.value = ViewAction.MovieUnliked
        state.actions.value = ViewAction.RemoveUnlikedMovie(position)

        if (favoritesList.isNullOrEmpty()) {
            state.layout.value = ShowEmptyState
        }
    }

    private fun unlikeMovieFailure() {
        state.actions.value = ViewAction.UnlikeMovieFailure
    }
}