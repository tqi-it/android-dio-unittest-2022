package io.github.gustavobarbosab.movies.navigation.directions

import androidx.navigation.NavDirections

interface DirectionAdapter {
    fun createDirection(): NavDirections
}