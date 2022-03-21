package io.github.gustavobarbosab.movies.data.di

import dagger.Module
import dagger.Provides
import io.github.gustavobarbosab.movies.data.repository.ShowCaseRepositoryImpl
import io.github.gustavobarbosab.showcase.usecase.ShowCaseUseCase
import io.github.gustavobarbosab.showcase.usecase.ShowCaseUseCaseImpl

@Module
class ShowCaseDataModule {

    @Provides
    fun provideUseCase(repository: ShowCaseRepositoryImpl): ShowCaseUseCase =
        ShowCaseUseCaseImpl(repository)
}