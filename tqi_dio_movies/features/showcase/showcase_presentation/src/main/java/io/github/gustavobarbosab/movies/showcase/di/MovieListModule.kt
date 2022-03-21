package io.github.gustavobarbosab.movies.showcase.di

import dagger.Module
import dagger.Provides
import io.github.gustavobarbosab.core.di.scope.FeatureScope
import io.github.gustavobarbosab.movies.showcase.presentation.ShowCaseViewModelFactory
import io.github.gustavobarbosab.showcase.usecase.ShowCaseUseCase

@Module
class MovieListModule