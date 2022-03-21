package io.github.gustavobarbosab.movies.showcase.presentation

import androidx.lifecycle.MutableLiveData
import io.github.gustavobarbosab.commons.livedata.SingleLiveEvent
import io.github.gustavobarbosab.detail.model.MovieDetailDomain
import io.github.gustavobarbosab.movies.showcase.presentation.model.ShowCaseModel
import io.github.gustavobarbosab.showcase.model.MovieShowCase

class ShowCaseViewState {
    val bannerMovies: MutableLiveData<List<ShowCaseModel>> = MutableLiveData()
    val latestMovies: MutableLiveData<List<ShowCaseModel>> = MutableLiveData()
    val popularMovies: MutableLiveData<List<ShowCaseModel>> = MutableLiveData()
    val topRatedMovies: MutableLiveData<List<ShowCaseModel>> = MutableLiveData()
    val action: SingleLiveEvent<ViewAction> = SingleLiveEvent()

    sealed class ViewAction() {
        object ErrorLoadBanner : ViewAction()
        object ShowLatestLoading : ViewAction()
        object ErrorLoadLatest : ViewAction()
        object ShowPopularLoading : ViewAction()
        object ErrorLoadPopular : ViewAction()
        object ShowTopRatedLoading : ViewAction()
        object ErrorLoadTopRated : ViewAction()
        object RedirectToSearch : ViewAction()
        class ShowMovieDetails(val movie: MovieDetailDomain) : ViewAction()
    }
}