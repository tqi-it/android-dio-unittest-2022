package io.github.gustavobarbosab.movies.showcase.presentation

import androidx.lifecycle.viewModelScope
import io.github.gustavobarbosab.commons.extension.launchMain
import io.github.gustavobarbosab.commons.ui.base.BaseViewModel
import io.github.gustavobarbosab.detail.model.MovieDetailDomain
import io.github.gustavobarbosab.showcase.usecase.ShowCaseUseCase
import io.github.gustavobarbosab.showcase.model.MovieShowCase
import io.github.gustavobarbosab.movies.showcase.presentation.ShowCaseViewState.ViewAction.*
import io.github.gustavobarbosab.movies.showcase.presentation.model.ShowCaseModel
import io.github.gustavobarbosab.movies.showcase.presentation.model.ShowCasePresentationMapper
import io.gustavobarbosab.suspendresult.CoroutineResultHandler

class ShowCaseViewModel(
    private val useCase: ShowCaseUseCase
) : BaseViewModel<ShowCaseViewState>(), CoroutineResultHandler {

    override val state = ShowCaseViewState()

    private val mapper = ShowCasePresentationMapper()

    init {
        getPopularMovies()
        getLatestMovies()
        getBannerMovies()
        getTopRatedMovies()
    }

    fun onSearchMovie() {
        state.action.value = RedirectToSearch
    }

    fun showDetails(movie: ShowCaseModel) {
        state.action.value = ShowMovieDetails(mapper.map(movie))
    }

    fun getBannerMovies() {
        viewModelScope.launchMain {
            handleResult(
                result = useCase.getPlayingNow(),
                onSuccess = this@ShowCaseViewModel::getBannerMoviesSuccess,
                onError = { state.action.value = ErrorLoadBanner }
            )
        }
    }

    private fun getBannerMoviesSuccess(list: List<MovieShowCase>?) {
        val mappedList = list?.map(mapper::map)
        state.bannerMovies.value = mappedList
    }

    fun getPopularMovies() {
        viewModelScope.launchMain {
            state.action.value = ShowPopularLoading
            handleResult(
                result = useCase.getPopularMovies(),
                onSuccess = this@ShowCaseViewModel::getPopularSuccess,
                onError = { state.action.value = ErrorLoadPopular }
            )
        }
    }

    private fun getPopularSuccess(list: List<MovieShowCase>?) {
        val mappedList = list?.map(mapper::map)
        state.popularMovies.value = mappedList
    }

    fun getLatestMovies() {
        viewModelScope.launchMain {
            state.action.value = ShowLatestLoading
            handleResult(
                result = useCase.getLatestMovies(),
                onSuccess = this@ShowCaseViewModel::getLatestSuccess,
                onError = { state.action.value = ErrorLoadLatest }
            )
        }
    }

    private fun getLatestSuccess(list: List<MovieShowCase>?) {
        val mappedList = list?.map(mapper::map)
        state.latestMovies.value = mappedList
    }

    fun getTopRatedMovies() {
        viewModelScope.launchMain {
            state.action.value = ShowTopRatedLoading
            handleResult(
                result = useCase.getTopRatedMovies(),
                onSuccess = this@ShowCaseViewModel::getTopRatedSuccess,
                onError = { state.action.value = ErrorLoadTopRated }
            )
        }
    }

    private fun getTopRatedSuccess(list: List<MovieShowCase>?) {
        val mappedList = list?.map(mapper::map)
        state.topRatedMovies.value = mappedList
    }
}