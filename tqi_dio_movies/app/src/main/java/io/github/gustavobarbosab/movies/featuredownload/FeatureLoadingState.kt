package io.github.gustavobarbosab.movies.featuredownload

import androidx.lifecycle.MutableLiveData
import io.github.gustavobarbosab.commons.livedata.SingleLiveEvent

class FeatureLoadingState {
    val action: MutableLiveData<ViewAction> = SingleLiveEvent()

    sealed class ViewAction {
        object DownloadStarted : ViewAction()
        object DownloadCanceled : ViewAction()
        object DownloadFailed : ViewAction()
        class UpdateDownloadProgress(val progress: Int) : ViewAction()
    }
}