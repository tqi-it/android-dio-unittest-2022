package io.github.gustavobarbosab.core.network.interceptor

import io.github.gustavobarbosab.core.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.util.*

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val originalHttpUrl: HttpUrl = original.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter(PARAM_API_KEY, BuildConfig.MOVIE_API_KEY)
            .addQueryParameter(PARAM_LANGUAGE, Locale.getDefault().toLanguageTag())
            .build()

        val requestBuilder: Request.Builder = original.newBuilder()
            .url(url)

        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }

    companion object {
        const val PARAM_API_KEY = "api_key"
        const val PARAM_LANGUAGE = "language"
    }
}