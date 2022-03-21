package io.github.gustavobarbosab.commons.extension

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun CoroutineScope.launchMain(block: suspend CoroutineScope.() -> Unit) =
    this.launch(context = Dispatchers.Main, block = block)