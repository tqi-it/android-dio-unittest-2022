package io.github.gustavobarbosab.commons.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.google.android.play.core.splitcompat.SplitCompat
import io.github.gustavobarbosab.commons.widget.progress.MoovieLoading

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    abstract val layoutId: Int
    lateinit var binding: T
    private val loading = MoovieLoading()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SplitCompat.install(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews(savedInstanceState)
    }

    abstract fun initializeViews(savedInstance: Bundle?)

    /**
     *  -- ATTENTION --
     * This progress is used to improve the development velocity
     * but the best practice is use shimmer or a progress that won't
     * block user's screen.
     */
    fun showLoading() {
        loading.showLoading(childFragmentManager)
    }

    fun hideLoading() {
        loading.hideLoading()
    }

}