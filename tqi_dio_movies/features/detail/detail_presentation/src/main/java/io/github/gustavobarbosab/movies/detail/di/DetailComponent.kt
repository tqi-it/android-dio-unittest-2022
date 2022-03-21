package io.github.gustavobarbosab.movies.detail.di

import dagger.Component
import io.github.gustavobarbosab.core.di.scope.FeatureScope
import io.github.gustavobarbosab.movies.detail.presentation.MovieDetailFragment
import io.github.gustavobarbosab.movies.di.AppComponent

@Component(
    dependencies = [AppComponent::class],
    modules = [DetailModule::class]
)
@FeatureScope
interface DetailComponent {

    fun inject(fragment: MovieDetailFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: AppComponent): DetailComponent
    }
}