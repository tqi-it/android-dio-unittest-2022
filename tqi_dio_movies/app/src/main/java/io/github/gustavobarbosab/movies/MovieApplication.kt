package io.github.gustavobarbosab.movies

import android.content.Context
import com.google.android.play.core.splitcompat.SplitCompat
import com.google.android.play.core.splitcompat.SplitCompatApplication
import io.github.gustavobarbosab.movies.di.AppComponent
import io.github.gustavobarbosab.movies.di.DaggerAppComponent

class MovieApplication : SplitCompatApplication() {

    lateinit var appComponent: AppComponent

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        SplitCompat.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()
        appComponent.inject(this)
    }
}