package io.github.gustavobarbosab.commons.widget.toolbar.buttons

import androidx.annotation.DrawableRes
import io.github.gustavobarbosab.commons.R

enum class ShortcutButtonType(@DrawableRes val icon: Int) {
    SEARCH(R.drawable.ic_search),
    NONE(android.R.color.transparent)
}