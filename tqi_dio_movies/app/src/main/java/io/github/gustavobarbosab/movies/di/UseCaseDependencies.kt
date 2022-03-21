package io.github.gustavobarbosab.movies.di

import io.github.gustavobarbosab.movies.favorites.domain.usecase.FavoriteMovieUseCase
import io.github.gustavobarbosab.showcase.usecase.ShowCaseUseCase

interface UseCaseDependencies {
    fun provideShowCaseUseCase(): ShowCaseUseCase
    fun provideDetailUseCase(): FavoriteMovieUseCase
}