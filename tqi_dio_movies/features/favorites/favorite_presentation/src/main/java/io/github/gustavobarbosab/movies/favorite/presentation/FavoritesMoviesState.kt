package io.github.gustavobarbosab.movies.favorite.presentation

import androidx.lifecycle.MutableLiveData
import io.github.gustavobarbosab.commons.livedata.SingleLiveEvent
import io.github.gustavobarbosab.movies.favorite.model.FavoriteModel

class FavoritesMoviesState {
    val layout = MutableLiveData<LayoutState>()
    val actions = SingleLiveEvent<ViewAction>()

    sealed class ViewAction {
        object ShowLoading : ViewAction()
        object HideLoading : ViewAction()
        object MovieUnliked : ViewAction()
        object UnlikeMovieFailure : ViewAction()
        class RemoveUnlikedMovie(val position: Int) : ViewAction()
    }

    sealed class LayoutState {
        object ShowTryAgain : LayoutState()
        class ShowRecyclerView(val list: MutableList<FavoriteModel>) : LayoutState()
        object ShowEmptyState : LayoutState()
        object HideAll : LayoutState()
    }
}