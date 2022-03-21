package io.github.gustavobarbosab.movies.showcase.presentation.banner

import android.os.Bundle
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import io.github.gustavobarbosab.commons.ui.base.BaseFragment
import io.github.gustavobarbosab.commons.widget.carousel.CarouselAutoScroll
import io.github.gustavobarbosab.commons.widget.carousel.DepthPageTransformer
import io.github.gustavobarbosab.movies.showcase.R
import io.github.gustavobarbosab.movies.showcase.databinding.FragmentBannerMoviesBinding
import io.github.gustavobarbosab.showcase.model.MovieShowCase
import io.github.gustavobarbosab.movies.showcase.presentation.ShowCaseViewModel
import io.github.gustavobarbosab.movies.showcase.presentation.ShowCaseViewState
import io.github.gustavobarbosab.movies.showcase.presentation.model.ShowCaseModel

class BannerMoviesFragment : BaseFragment<FragmentBannerMoviesBinding>() {

    private lateinit var viewModel: ShowCaseViewModel

    private val bannerTopAdapter = PagerCarouselAdapter(this::onItemClicked)

    override val layoutId: Int = R.layout.fragment_banner_movies

    private var carouselAutoScroll: CarouselAutoScroll? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireParentFragment()).get(ShowCaseViewModel::class.java)
    }

    override fun initializeViews(savedInstance: Bundle?) {
        carouselAutoScroll = CarouselAutoScroll.setupWithViewPager(
            owner = viewLifecycleOwner,
            viewPager = binding.bannerTop,
            autoScrollTimeMillis = 5000L,
            autoProgressView = binding.autoProgress
        )
        binding.apply {
            bannerTop.adapter = bannerTopAdapter
            bannerTop.setPageTransformer(DepthPageTransformer())
            bannerTryAgain.buttonListener(viewModel::getBannerMovies)
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.state.bannerMovies.observe(viewLifecycleOwner) {
            tryAgainVisibility(visible = false)
            bannerTopAdapter.items = it
        }
        viewModel.state.action.observe(viewLifecycleOwner, {
            when (it) {
                ShowCaseViewState.ViewAction.ErrorLoadBanner -> tryAgainVisibility(visible = true)
                else -> {
                }
            }
        })
    }

    private fun tryAgainVisibility(visible: Boolean) = with(binding) {
        bannerGroup.isGone = visible
        bannerTryAgain.isVisible = visible
    }

    private fun onItemClicked(movie: ShowCaseModel) {
        viewModel.showDetails(movie)
    }
}