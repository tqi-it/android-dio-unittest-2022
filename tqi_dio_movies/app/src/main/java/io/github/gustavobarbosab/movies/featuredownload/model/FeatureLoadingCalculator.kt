package io.github.gustavobarbosab.movies.featuredownload.model

class FeatureLoadingCalculator {

    fun progress(bytesDownloaded: Long, bytesTotal: Long) = try {
        (bytesDownloaded.toInt() / bytesTotal.toInt()) * ONE_HUNDRED_PERCENT
    } catch (e: Exception) {
        ONE_HUNDRED_PERCENT
    }

    companion object {
        const val ONE_HUNDRED_PERCENT = 100
    }
}