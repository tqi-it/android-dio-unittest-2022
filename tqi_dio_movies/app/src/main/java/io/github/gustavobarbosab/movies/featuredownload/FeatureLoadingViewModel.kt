package io.github.gustavobarbosab.movies.featuredownload

import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import io.github.gustavobarbosab.commons.ui.base.BaseViewModel
import io.github.gustavobarbosab.movies.featuredownload.FeatureLoadingState.ViewAction
import io.github.gustavobarbosab.movies.featuredownload.model.FeatureLoadingCalculator

class FeatureLoadingViewModel(
    private val calculator: FeatureLoadingCalculator
) : BaseViewModel<FeatureLoadingState>() {

    override val state: FeatureLoadingState = FeatureLoadingState()

    fun downloadCancelled() {
        state.action.value = ViewAction.DownloadCanceled
    }

    fun downloadFailed() {
        state.action.value = ViewAction.DownloadFailed
    }

    fun progressChanged(status: Int, bytesDownloaded: Long, bytesTotal: Long) {
        val progress = calculator.progress(bytesDownloaded, bytesTotal)
        state.action.value = ViewAction.UpdateDownloadProgress(progress)
        updateState(status)
    }

    private fun updateState(status: Int) {
        state.action.value = when (status) {
            SplitInstallSessionStatus.DOWNLOADING,
            SplitInstallSessionStatus.INSTALLING -> ViewAction.DownloadStarted
            SplitInstallSessionStatus.FAILED -> ViewAction.DownloadFailed
            else -> null
        } ?: return
    }
}