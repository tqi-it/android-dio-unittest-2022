package io.github.gustavobarbosab.movies.detail.presentation

import androidx.lifecycle.viewModelScope
import io.github.gustavobarbosab.commons.extension.launchMain
import io.github.gustavobarbosab.commons.ui.base.BaseViewModel
import io.gustavobarbosab.suspendresult.CoroutineResultHandler
import io.github.gustavobarbosab.movies.detail.model.DetailsButtonType
import io.github.gustavobarbosab.movies.detail.model.DetailModel
import io.github.gustavobarbosab.movies.detail.model.DetailPresentationMapper
import io.github.gustavobarbosab.movies.detail.presentation.DetailMovieState.ViewAction
import io.github.gustavobarbosab.movies.favorites.domain.model.MovieState
import io.github.gustavobarbosab.movies.favorites.domain.usecase.FavoriteMovieUseCase
import io.github.gustavobarbosab.movies.navigation.arguments.detail.MovieDetailArgument
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val favoriteUseCase: FavoriteMovieUseCase
) : BaseViewModel<DetailMovieState>(), CoroutineResultHandler {

    override val state: DetailMovieState = DetailMovieState()
    private val mapper = DetailPresentationMapper()

    private lateinit var movieSelected: DetailModel

    fun init(movie: MovieDetailArgument) {
        if (isScreenAlreadyCreated())
            return

        movieSelected = mapper.map(movie)
        state.movie.value = movieSelected
        handleFavoriteState()
    }

    private fun isScreenAlreadyCreated() = this::movieSelected.isInitialized

    private fun handleFavoriteState() {
        viewModelScope.launchMain {
            state.actions.value = ViewAction.ShowLoading
            handleResult(
                favoriteUseCase.isMovieFavorite(movieSelected.id),
                this@MovieDetailViewModel::searchFavoriteMovieSuccess,
                this@MovieDetailViewModel::searchFavoritesFailure
            )
            state.actions.value = ViewAction.HideLoading
        }
    }

    private fun searchFavoriteMovieSuccess(movieState: MovieState?) {
        state.favoriteButtonState.value = when (movieState) {
            MovieState.MovieLiked -> DetailsButtonType.Filled
            else -> DetailsButtonType.Outline
        }
    }

    private fun searchFavoritesFailure() {
        state.actions.value = ViewAction.StartScreenFailure
    }

    fun favoriteMovie() {
        viewModelScope.launchMain {
            state.actions.value = ViewAction.ShowLoading
            val detailDomain = mapper.map(movieSelected)
            handleResult(
                favoriteUseCase.updateFavoriteMovie(detailDomain),
                this@MovieDetailViewModel::favoriteMovieSuccess,
                this@MovieDetailViewModel::favoriteMovieFailure
            )
            state.actions.value = ViewAction.HideLoading
        }
    }

    private fun favoriteMovieSuccess(movieState: MovieState?) {
        when (movieState) {
            MovieState.MovieLiked -> updateButtonState(
                DetailsButtonType.Filled,
                ViewAction.MovieLiked
            )
            else -> updateButtonState(DetailsButtonType.Outline, ViewAction.MovieUnliked)
        }
    }

    private fun updateButtonState(newState: DetailsButtonType, viewAction: ViewAction) {
        state.favoriteButtonState.value = newState
        state.actions.value = viewAction
    }

    private fun favoriteMovieFailure() {
        state.actions.value = ViewAction.FavoriteMovieFailure
    }
}