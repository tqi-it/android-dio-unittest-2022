package io.github.gustavobarbosab.movies.data.datasources.local

import io.github.gustavobarbosab.core.database.MovieDao
import javax.inject.Inject

class ShowCaseLocalDataSource @Inject constructor(
    val movieDao: MovieDao
)