package io.github.gustavobarbosab.commons.ui.extension

import android.content.Context
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import io.github.gustavobarbosab.commons.R

fun ImageView.loadImage(url: String) {
    Glide
        .with(context)
        .load(url)
        .placeholder(ImageViewHelper.createProgress(context))
        .into(this)
}

object ImageViewHelper {
    fun createProgress(context: Context) = CircularProgressDrawable(context).also {
        it.setColorSchemeColors(
            ContextCompat.getColor(context, R.color.colorAccent),
            ContextCompat.getColor(context, android.R.color.white),
            ContextCompat.getColor(context, R.color.colorAccent)
        )
        it.centerRadius = 30f
        it.strokeWidth = 5f
        it.start()
    }
}
