package io.github.gustavobarbosab.commons.widget.snackbar

import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.google.android.material.snackbar.Snackbar
import io.github.gustavobarbosab.commons.R

// TODO melhorar o código talvez criando um builder para a snackbar
fun FragmentActivity.showSnackBar(type: SnackBarType, message: String) {
    val view = findViewById<View>(android.R.id.content)
    val snackbar = Snackbar.make(view, "", Snackbar.LENGTH_SHORT)

    val snackBarLayout = snackbar.view as Snackbar.SnackbarLayout
    snackBarLayout.setBackgroundColor(
        ContextCompat.getColor(
            this,
            android.R.color.transparent
        )
    )

    val snackText = snackBarLayout.findViewById<TextView>(
        com.google.android.material.R.id.snackbar_text
    )

    val snackButton = snackBarLayout.findViewById<View>(
        com.google.android.material.R.id.snackbar_action
    )

    snackText.visibility = View.INVISIBLE
    snackButton.visibility = View.GONE

    val newLayout = LayoutInflater.from(this)
        .inflate(
            R.layout.component_snackbar,
            snackBarLayout,
            false
        )

    val text = newLayout.findViewById<TextView>(R.id.snack_message)
    val image = newLayout.findViewById<ImageView>(R.id.snack_icon)
    val background = newLayout.findViewById<ConstraintLayout>(R.id.snack_background)
    val button = newLayout.findViewById<Button>(R.id.snack_confirmation)

    text.text = message
    image.setImageResource(type.imageResource)
    background.setBackgroundColor(ContextCompat.getColor(this, type.color))
    button.setOnClickListener { snackbar.dismiss() }

    snackBarLayout.setPadding(0, 0, 0, 0)
    snackBarLayout.addView(newLayout, 0)

    snackbar.show()
}