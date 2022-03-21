package io.github.gustavobarbosab.core.config

interface MoovieConfig {
    fun formatImageUrl(endpoint: String?): String
    fun appBaseUrl(): String
}