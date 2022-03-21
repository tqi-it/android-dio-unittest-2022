package io.github.gustavobarbosab.movies.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import java.lang.Exception

interface MoovieNavigation {

    fun navigate(
        origin: Fragment,
        destination: NavDirections,
        onError: (Exception) -> Unit
    )

    fun navigate(
        origin: Fragment,
        destination: NavDirections
    )

    fun popStack(origin: Fragment)

    fun navigate(origin: Fragment, destination: Fragment)
}