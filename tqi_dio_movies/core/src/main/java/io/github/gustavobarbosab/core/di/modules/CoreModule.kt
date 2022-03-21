package io.github.gustavobarbosab.core.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import io.github.gustavobarbosab.core.config.AppConfigWrapper
import io.github.gustavobarbosab.core.config.MoovieConfig
import io.github.gustavobarbosab.core.database.MoovieDataBase
import io.github.gustavobarbosab.core.network.services.movies.MovieApi
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class CoreModule {

    @Provides
    @Singleton
    fun provideAppWrapper(): MoovieConfig = AppConfigWrapper()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): MovieApi = retrofit.create(MovieApi::class.java)

    @Provides
    @Singleton
    fun provideMovieDataBase(applicationContext: Context) =
        Room.databaseBuilder(
            applicationContext,
            MoovieDataBase::class.java,
            "moovie-database"
        ).build()

    @Provides
    @Singleton
    fun provideMovieDao(dataBase: MoovieDataBase) = dataBase.movieDao()
}