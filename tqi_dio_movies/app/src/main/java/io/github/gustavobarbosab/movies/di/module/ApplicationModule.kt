package io.github.gustavobarbosab.movies.di.module

import dagger.Module
import dagger.Provides
import io.github.gustavobarbosab.movies.navigation.MoovieNavigation
import io.github.gustavobarbosab.movies.navigation.MoovieNavigationImpl
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun provideNavigation(): MoovieNavigation = MoovieNavigationImpl()
}