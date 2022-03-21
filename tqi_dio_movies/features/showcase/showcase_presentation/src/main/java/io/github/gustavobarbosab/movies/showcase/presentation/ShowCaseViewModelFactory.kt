package io.github.gustavobarbosab.movies.showcase.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.github.gustavobarbosab.core.di.scope.FeatureScope
import io.github.gustavobarbosab.showcase.usecase.ShowCaseUseCase
import javax.inject.Inject

@FeatureScope
class ShowCaseViewModelFactory @Inject constructor(
    private val useCase: ShowCaseUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = ShowCaseViewModel(useCase) as T
}