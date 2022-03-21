package io.github.gustavobarbosab.commons.widget.error

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import io.github.gustavobarbosab.commons.R

class TryAgain @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val tvMessage: TextView
    private val btTryAgain: TextView
    private var btListener = {}

    init {
        LayoutInflater
            .from(context)
            .inflate(R.layout.component_try_again, this, true)
        tvMessage = findViewById(R.id.error_title)
        btTryAgain = findViewById(R.id.error_button)
        btTryAgain.setOnClickListener { btListener() }

        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.TryAgain,
            defStyleAttr,
            0
        )
        setupAttributes(typedArray)
        typedArray.recycle()
    }

    private fun setupAttributes(typedArray: TypedArray) {
        val message = typedArray.getString(R.styleable.TryAgain_android_text).orEmpty()
        val buttonText = typedArray.getString(R.styleable.TryAgain_android_button).orEmpty()
        setMessage(message)
        setButtonText(buttonText)
    }

    fun setMessage(message: String) {
        tvMessage.text = message
    }

    fun buttonListener(listener: () -> Unit) {
        btListener = listener
    }

    fun setButtonText(text: String) {
        btTryAgain.text = text
    }
}