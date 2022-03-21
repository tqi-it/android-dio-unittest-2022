package io.github.gustavobarbosab.movies.featuredownload

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.dynamicfeatures.fragment.ui.AbstractProgressFragment
import io.github.gustavobarbosab.commons.widget.toolbar.buttons.BackButtonType
import io.github.gustavobarbosab.movies.R
import io.github.gustavobarbosab.movies.databinding.FragmentBasicLoadingBinding
import io.github.gustavobarbosab.movies.extension.applicationToolbar
import io.github.gustavobarbosab.movies.featuredownload.FeatureLoadingState.ViewAction

class FeatureLoadingFragment : AbstractProgressFragment() {

    private lateinit var binding: FragmentBasicLoadingBinding

    private lateinit var viewModel: FeatureLoadingViewModel

    private val factory = FeatureLoadingViewModelFactory()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBasicLoadingBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(FeatureLoadingViewModel::class.java)
        observeState()
        configureToolbar(BackButtonType.NONE)
    }

    private fun configureToolbar(backButton: BackButtonType) = applicationToolbar {
        title = getString(R.string.feature_loading_toolbar_title)
        backButtonType = backButton
    }

    override fun onCancelled() {
        viewModel.downloadCancelled()
    }

    override fun onFailed(errorCode: Int) {
        viewModel.downloadFailed()
    }

    override fun onProgress(
        status: Int,
        bytesDownloaded: Long,
        bytesTotal: Long
    ) {
        viewModel.progressChanged(status, bytesDownloaded, bytesTotal)
    }

    private fun observeState() {
        viewModel.state.action.observe(viewLifecycleOwner) {
            when (it) {
                is ViewAction.UpdateDownloadProgress -> updateProgress(it.progress)
                ViewAction.DownloadStarted -> updateMessage(R.string.feature_loading_download_started)
                ViewAction.DownloadCanceled -> downloadProblem(R.string.feature_loading_download_canceled)
                ViewAction.DownloadFailed -> downloadProblem(R.string.feature_loading_download_failed)
            }
        }
    }

    private fun downloadProblem(message: Int) {
        configureToolbar(BackButtonType.CLOSE)
        updateMessage(message)
    }

    private fun updateMessage(message: Int) {
        binding.text.setText(message)
    }

    private fun updateProgress(progress: Int) {
        binding.progressBar.progress = progress
    }
}