package io.github.gustavobarbosab.movies.favorites.data.mapper

import android.graphics.Movie
import io.github.gustavobarbosab.core.database.dto.MovieDto
import io.github.gustavobarbosab.movies.favorites.domain.model.MovieFavorite
import java.text.SimpleDateFormat
import java.util.*

class FavoriteMovieDataMapper {

    fun map(movie: MovieFavorite, favorite: Boolean) = MovieDto(
        movie.id,
        movie.name,
        movie.poster,
        movie.imageUrl,
        movie.description,
        actualDateAndTime(),
        favorite
    )

    private fun actualDateAndTime(): String {
        val format = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        val actualDate = Date()
        return format.format(actualDate)
    }

    fun map(movie: MovieDto) = MovieFavorite(
        movie.id,
        movie.name,
        movie.description,
        movie.imageUrl,
        movie.posterUrl,
        movie.savedDate
    )
}