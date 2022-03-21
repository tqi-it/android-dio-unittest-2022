package io.github.gustavobarbosab.movies.showcase.di

import dagger.Component
import io.github.gustavobarbosab.core.di.scope.FeatureScope
import io.github.gustavobarbosab.movies.di.AppComponent
import io.github.gustavobarbosab.movies.showcase.presentation.ShowCaseFragment

@FeatureScope
@Component(
    modules = [MovieListModule::class],
    dependencies = [AppComponent::class]
)
interface MovieListComponent {

    fun inject(fragment: ShowCaseFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: AppComponent): MovieListComponent
    }
}