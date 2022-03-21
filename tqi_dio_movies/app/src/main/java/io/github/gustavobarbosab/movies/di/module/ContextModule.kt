package io.github.gustavobarbosab.movies.di.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface ContextModule {
    @Binds
    @Singleton
    fun provideContext(application: Application): Context
}