package io.github.gustavobarbosab.core.di.modules

import dagger.Module
import dagger.Provides
import io.github.gustavobarbosab.core.BuildConfig
import io.github.gustavobarbosab.core.config.MoovieConfig
import io.github.gustavobarbosab.core.network.adapter.CoroutineResponseAdapterFactory
import io.github.gustavobarbosab.core.network.interceptor.AuthInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        appConfigWrapper: MoovieConfig
    ): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(appConfigWrapper.appBaseUrl())
            .client(okHttpClient)
            .addCallAdapterFactory(CoroutineResponseAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()


    @Provides
    @Singleton
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
        @Named(BuildConfig.BUILD_TYPE) loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient()
            .newBuilder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .callTimeout(20, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideAuthInterceptor(): AuthInterceptor = AuthInterceptor()

    @Provides
    @Singleton
    @Named("debug")
    fun provideLoggingInterceptorDebug(): HttpLoggingInterceptor =
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    @Named("release")
    fun provideLoggingInterceptorRelease(): HttpLoggingInterceptor =
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
}