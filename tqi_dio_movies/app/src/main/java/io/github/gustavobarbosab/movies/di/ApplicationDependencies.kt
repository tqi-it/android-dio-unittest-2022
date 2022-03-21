package io.github.gustavobarbosab.movies.di

import android.app.Application
import android.content.Context
import io.github.gustavobarbosab.movies.navigation.MoovieNavigation

interface ApplicationDependencies {
    fun provideApplication(): Application
    fun provideContext(): Context
    fun provideNavigation(): MoovieNavigation
}