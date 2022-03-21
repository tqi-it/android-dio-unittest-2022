package io.github.gustavobarbosab.commons.widget.autoprogress

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.ProgressBar
import io.github.gustavobarbosab.commons.R

class AutoProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ProgressBar(context, attrs, defStyleAttr) {

    private val counter: ProgressCounter

    init {
        max = 100
        progress = 0

        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.AutoProgressView,
            defStyleAttr,
            0
        )
        val progressVelocity = typedArray.getInt(
            R.styleable.AutoProgressView_progressVelocityMillis,
            0
        )
        val progressLoop = typedArray.getBoolean(
            R.styleable.AutoProgressView_progressLoop,
            false
        )
        counter = ProgressCounter(this::updateProgress, progressVelocity.toLong(), progressLoop)
        typedArray.recycle()
    }

    fun startProgress() {
        progress = 0
        counter.startCounter()
    }

    private fun updateProgress(progress: Int) {
        this.progress = progress
    }

    fun stopProgress() {
        counter.stopCounter()
    }
}