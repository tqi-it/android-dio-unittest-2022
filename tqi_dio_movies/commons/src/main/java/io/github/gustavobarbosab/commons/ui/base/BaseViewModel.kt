package io.github.gustavobarbosab.commons.ui.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel<STATE> : ViewModel() {
    abstract val state: STATE
}