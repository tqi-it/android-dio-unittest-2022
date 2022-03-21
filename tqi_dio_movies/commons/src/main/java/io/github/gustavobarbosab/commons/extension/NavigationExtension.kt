package io.github.gustavobarbosab.commons.extension

import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment

fun FragmentManager.navController(fragmentId: Int) =
    (findFragmentById(fragmentId) as NavHostFragment).navController

fun FragmentManager.lazyNavController(fragmentId: Int) =
    lazy { navController(fragmentId) }