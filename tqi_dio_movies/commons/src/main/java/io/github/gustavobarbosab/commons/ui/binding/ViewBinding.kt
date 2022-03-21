package io.github.gustavobarbosab.commons.ui.binding

import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

fun <T : ViewDataBinding> Fragment.dataBinding(layoutId: Int): Lazy<T> = lazy {
    DataBindingUtil.inflate(layoutInflater, layoutId, view as? ViewGroup?, false)
}