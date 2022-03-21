package io.github.gustavobarbosab.movies.featuredownload

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.gustavobarbosab.movies.featuredownload.model.FeatureLoadingCalculator

class FeatureLoadingViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        FeatureLoadingViewModel(FeatureLoadingCalculator()) as T
}