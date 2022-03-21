package io.github.gustavobarbosab.movies.navigation.arguments.detail

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
class MovieDetailArgument(
    val id: Long,
    val name: String,
    val description: String,
    val imageUrl: String,
    val poster: String
) : Parcelable