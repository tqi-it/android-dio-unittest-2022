package io.github.gustavobarbosab.movies.navigation

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import io.github.gustavobarbosab.movies.extension.findAppNavController
import io.github.gustavobarbosab.movies.extension.navigateSafely
import java.lang.Exception

class MoovieNavigationImpl: MoovieNavigation {

    override fun navigate(
        origin: Fragment,
        destination: NavDirections,
        onError: (Exception) -> Unit
    ) {
        origin.findAppNavController().navigateSafely(destination, onError)
    }

    override fun navigate(
        origin: Fragment,
        destination: NavDirections
    ) {
        origin.findAppNavController().navigate(destination)
    }

    override fun popStack(origin: Fragment) {
        origin.findAppNavController().popBackStack()
    }

    override fun navigate(origin: Fragment, destination: Fragment) {
        TODO()
    }
}