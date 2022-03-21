package io.github.gustavobarbosab.movies.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import io.github.gustavobarbosab.core.di.dependencies.CoreDependencies
import io.github.gustavobarbosab.core.di.modules.CoreModule
import io.github.gustavobarbosab.core.di.modules.NetworkModule
import io.github.gustavobarbosab.movies.MovieApplication
import io.github.gustavobarbosab.movies.di.module.ApplicationModule
import io.github.gustavobarbosab.movies.di.module.ContextModule
import io.github.gustavobarbosab.movies.di.module.DataModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ContextModule::class,
        ApplicationModule::class,
        CoreModule::class,
        NetworkModule::class,
        DataModule::class
    ]
)
interface AppComponent :
    ApplicationDependencies,
    CoreDependencies,
    UseCaseDependencies {

    fun inject(application: MovieApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
