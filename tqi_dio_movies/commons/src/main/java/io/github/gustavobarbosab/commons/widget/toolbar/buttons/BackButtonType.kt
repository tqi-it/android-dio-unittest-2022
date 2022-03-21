package io.github.gustavobarbosab.commons.widget.toolbar.buttons

import androidx.annotation.DrawableRes
import io.github.gustavobarbosab.commons.R

enum class BackButtonType(@DrawableRes val icon: Int) {
    CLOSE(R.drawable.ic_close),
    ARROW(R.drawable.ic_back),
    NONE(android.R.color.transparent)
}