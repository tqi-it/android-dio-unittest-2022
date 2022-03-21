package io.github.gustavobarbosab.commons.widget.scrollablemovie

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import io.github.gustavobarbosab.commons.R
import io.github.gustavobarbosab.commons.databinding.ComponentScrollableMoviesBinding
import io.github.gustavobarbosab.commons.widget.scrollablemovie.ScrollableMoviesView.ScrollableVisibilityStates.*

class ScrollableMoviesView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var binding: ComponentScrollableMoviesBinding =
        ComponentScrollableMoviesBinding
            .inflate(
                LayoutInflater.from(context),
                this,
                true
            )

    var adapter: ScrollableMovieRecyclerViewAdapter? = null
        set(value) {
            field = value
            binding.moviesRecyclerView.adapter = value
        }


    init {
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.ScrollableMoviesView,
            defStyleAttr,
            0
        )
        readAttributes(typedArray)
        typedArray.recycle()
    }

    private fun readAttributes(typedArray: TypedArray) = with(typedArray) {
        val title = getString(R.styleable.ScrollableMoviesView_title).orEmpty()
        val message = getString(R.styleable.ScrollableMoviesView_tryAgainMessage).orEmpty()
        val buttonText = getString(R.styleable.ScrollableMoviesView_tryAgainButton).orEmpty()

        binding.moviesTitle.text = title
        binding.tryAgain.setMessage(message)
        binding.tryAgain.setButtonText(buttonText)
    }

    fun showShimmer() {
        setViewVisibilityState(ShimmerVisible)
    }

    fun showMovieList() {
        setViewVisibilityState(RecyclerViewVisible)
    }

    fun reloadListener(listener: () -> Unit) {
        binding.tryAgain.buttonListener(listener)
    }

    fun showTryAgain() {
        setViewVisibilityState(TryAgainVisible)
    }

    private fun setViewVisibilityState(state: ScrollableVisibilityStates) {
        when (state) {
            RecyclerViewVisible -> recyclerViewVisible()
            ShimmerVisible -> shimmerVisible()
            TryAgainVisible -> tryAgainVisible()
        }
    }

    private fun recyclerViewVisible() {
        binding.apply {
            moviesRecyclerView.isVisible = true
            tryAgain.isGone = true
            shimmerLayout.isGone = true
        }
    }

    private fun shimmerVisible() {
        binding.apply {
            shimmerLayout.isVisible = true
            moviesRecyclerView.isInvisible = true
            tryAgain.isGone = true
        }
    }

    private fun tryAgainVisible() {
        binding.apply {
            tryAgain.isVisible = true
            moviesRecyclerView.isInvisible = true
            shimmerLayout.isGone = true
        }
    }

    sealed class ScrollableVisibilityStates {
        object ShimmerVisible : ScrollableVisibilityStates()
        object TryAgainVisible : ScrollableVisibilityStates()
        object RecyclerViewVisible : ScrollableVisibilityStates()
    }
}