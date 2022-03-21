package io.github.gustavobarbosab.movies.di.module

import dagger.Module
import io.github.gustavobarbosab.movies.data.di.ShowCaseDataModule
import io.github.gustavobarbosab.movies.detail.data.di.DetailDataModule
import io.github.gustavobarbosab.movies.favorites.data.di.FavoritesDataModule

@Module(
    includes = [
        ShowCaseDataModule::class,
        DetailDataModule::class,
        FavoritesDataModule::class
    ]
)
interface DataModule