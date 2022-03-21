package io.github.gustavobarbosab.movies.favorites.data.di

import dagger.Module
import dagger.Provides
import io.github.gustavobarbosab.movies.favorites.data.FavoriteMovieRepositoryImpl
import io.github.gustavobarbosab.movies.favorites.domain.usecase.FavoriteMovieUseCase
import io.github.gustavobarbosab.movies.favorites.domain.usecase.FavoriteMovieUseCaseImpl

@Module
class FavoritesDataModule {
    @Provides
    fun provideUseCase(favoriteMovieRepository: FavoriteMovieRepositoryImpl): FavoriteMovieUseCase =
        FavoriteMovieUseCaseImpl(favoriteMovieRepository)
}