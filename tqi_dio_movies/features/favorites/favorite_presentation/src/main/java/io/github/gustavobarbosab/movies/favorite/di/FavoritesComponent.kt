package io.github.gustavobarbosab.movies.favorite.di

import dagger.Component
import io.github.gustavobarbosab.core.di.scope.FeatureScope
import io.github.gustavobarbosab.movies.favorite.presentation.FavoriteMoviesFragment
import io.github.gustavobarbosab.movies.di.AppComponent

@Component(
    dependencies = [AppComponent::class],
    modules = [FavoritesModule::class]
)
@FeatureScope
interface FavoritesComponent {

    fun inject(fragment: FavoriteMoviesFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: AppComponent): FavoritesComponent
    }
}