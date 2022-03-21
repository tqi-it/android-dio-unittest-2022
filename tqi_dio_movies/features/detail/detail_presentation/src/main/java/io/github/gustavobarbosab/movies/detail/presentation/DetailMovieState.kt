package io.github.gustavobarbosab.movies.detail.presentation

import androidx.lifecycle.MutableLiveData
import io.github.gustavobarbosab.commons.livedata.SingleLiveEvent
import io.github.gustavobarbosab.movies.detail.model.DetailModel
import io.github.gustavobarbosab.movies.detail.model.DetailsButtonType

class DetailMovieState {
    val movie = MutableLiveData<DetailModel>()
    val favoriteButtonState = MutableLiveData<DetailsButtonType>()
    val actions: MutableLiveData<ViewAction> = SingleLiveEvent()

    sealed class ViewAction() {
        object ShowLoading : ViewAction()
        object HideLoading : ViewAction()
        object MovieLiked : ViewAction()
        object MovieUnliked : ViewAction()
        object FavoriteMovieFailure : ViewAction()
        object StartScreenFailure : ViewAction()
    }
}