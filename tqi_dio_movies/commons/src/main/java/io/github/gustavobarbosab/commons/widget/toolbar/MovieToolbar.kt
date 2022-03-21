package io.github.gustavobarbosab.commons.widget.toolbar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.core.view.isVisible
import io.github.gustavobarbosab.commons.R
import io.github.gustavobarbosab.commons.widget.toolbar.buttons.BackButtonType
import io.github.gustavobarbosab.commons.widget.toolbar.buttons.ShortcutButtonType

class MovieToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val image: ImageView
    private val backButton: ImageView
    private val shortcutMenuIcon: ImageView
    private val title: TextView
    private var backButtonListener: (() -> Unit)? = null

    init {
        LayoutInflater
            .from(context)
            .inflate(R.layout.toolbar_movie, this, true)

        image = findViewById(R.id.toolbarIcon)
        backButton = findViewById(R.id.backIcon)
        shortcutMenuIcon = findViewById(R.id.shortcutIcon)
        title = findViewById(R.id.titleText)
    }

    fun setBackButtonType(buttonType: BackButtonType) {
        backButton.isGone = buttonType == BackButtonType.NONE
        backButton.setImageResource(buttonType.icon)
    }

    fun setupBackButton(function: () -> Unit) {
        backButtonListener = function
        backButton.setOnClickListener { backButtonListener?.invoke() }
    }

    fun setTitle(message: String) {
        image.visibility = View.GONE
        title.visibility = View.VISIBLE
        title.text = message
    }

    fun setShortcutListener(action: () -> Unit) {
        shortcutMenuIcon.setOnClickListener { action() }
    }

    fun setShortcutIcon(buttonType: ShortcutButtonType) {
        shortcutMenuIcon.isGone = buttonType == ShortcutButtonType.NONE
        shortcutMenuIcon.setImageResource(buttonType.icon)
    }

    fun showLogo(show: Boolean) {
        title.isGone = show
        image.isVisible = show
        image.setImageResource(R.drawable.ic_default_icon)
    }

    fun removeListener() = apply {
        backButtonListener = null
    }

    class Builder {
        var backButtonType: BackButtonType = BackButtonType.NONE
        var shortcutType = ShortcutButtonType.NONE
        var shortcutListener = {}
        var title: String = ""
        private var showLogo: Boolean = false

        fun showLogo() {
            showLogo = true
        }

        fun build(toolbar: MovieToolbar) = with(toolbar) {
            setBackButtonType(this@Builder.backButtonType)
            setShortcutIcon(this@Builder.shortcutType)
            setShortcutListener(this@Builder.shortcutListener)
            setTitle(this@Builder.title)
            showLogo(this@Builder.showLogo)
        }
    }
}