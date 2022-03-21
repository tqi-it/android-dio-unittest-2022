package io.github.gustavobarbosab.movies.showcase.presentation

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import io.github.gustavobarbosab.commons.extension.toast
import io.github.gustavobarbosab.commons.ui.base.BaseFragment
import io.github.gustavobarbosab.commons.widget.toolbar.buttons.ShortcutButtonType
import io.github.gustavobarbosab.core.di.scope.FeatureScope
import io.github.gustavobarbosab.detail.model.MovieDetailDomain
import io.github.gustavobarbosab.movies.BuildConfig.VERSION_NAME
import io.github.gustavobarbosab.movies.extension.applicationToolbar
import io.github.gustavobarbosab.movies.extension.requireAppComponent
import io.github.gustavobarbosab.movies.navigation.MoovieNavigation
import io.github.gustavobarbosab.movies.navigation.directions.showcase.ShowCaseDetailDirection
import io.github.gustavobarbosab.movies.showcase.R
import io.github.gustavobarbosab.movies.showcase.databinding.FragmentShowCaseBinding
import io.github.gustavobarbosab.movies.showcase.di.DaggerMovieListComponent
import io.github.gustavobarbosab.movies.showcase.di.MovieListComponent
import io.github.gustavobarbosab.movies.showcase.di.ShowCaseInjector
import io.github.gustavobarbosab.movies.showcase.presentation.ShowCaseViewState.ViewAction.*
import io.github.gustavobarbosab.movies.showcase.presentation.model.ShowCaseModel
import io.github.gustavobarbosab.movies.showcase.presentation.movielist.MovieListAdapter
import javax.inject.Inject

@FeatureScope
class ShowCaseFragment : BaseFragment<FragmentShowCaseBinding>(), ShowCaseInjector {

    @Inject
    lateinit var viewModelFactory: ShowCaseViewModelFactory

    @Inject
    lateinit var navigation: MoovieNavigation

    private lateinit var viewModel: ShowCaseViewModel

    private val latestAdapter = MovieListAdapter(this::onItemClicked)
    private val popularAdapter = MovieListAdapter(this::onItemClicked)
    private val topRatedAdapter = MovieListAdapter(this::onItemClicked)

    override val layoutId: Int = R.layout.fragment_show_case

    override var component: MovieListComponent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createComponent()
        viewModel = ViewModelProvider(this, viewModelFactory).get(ShowCaseViewModel::class.java)
    }

    private fun createComponent() {
        component = DaggerMovieListComponent.factory().create(requireAppComponent())
        component?.inject(this)
    }

    override fun initializeViews(savedInstance: Bundle?) {
        observeViewModel()
        setupToolbar()
        setupMovieList()
        setupVersion()
    }

    private fun setupMovieList() = with(binding) {
        latestMovies.adapter = latestAdapter
        topRated.adapter = topRatedAdapter
        popularMovies.adapter = popularAdapter

        latestMovies.reloadListener(viewModel::getLatestMovies)
        popularMovies.reloadListener(viewModel::getPopularMovies)
        topRated.reloadListener(viewModel::getTopRatedMovies)
    }

    private fun setupVersion() {
        binding.textVersion.text = getString(
            R.string.show_case_app_version,
            VERSION_NAME
        )
    }

    private fun setupToolbar() {
        applicationToolbar {
            showLogo()
            shortcutType = ShortcutButtonType.SEARCH
            shortcutListener = viewModel::onSearchMovie
        }
    }

    private fun observeViewModel() {
        viewModel.state.action.observe(viewLifecycleOwner, {
            when (it) {
                ShowLatestLoading -> binding.latestMovies.showShimmer()
                ErrorLoadLatest -> binding.latestMovies.showTryAgain()
                ShowPopularLoading -> binding.popularMovies.showShimmer()
                ErrorLoadPopular -> binding.popularMovies.showTryAgain()
                ShowTopRatedLoading -> binding.topRated.showShimmer()
                ErrorLoadTopRated -> binding.topRated.showTryAgain()
                RedirectToSearch -> context?.toast("Pesquisar")
                is ShowMovieDetails -> startDetails(it.movie)
                else -> {
                }
            }
        })

        viewModel.state.latestMovies.observe(viewLifecycleOwner) {
            latestAdapter.movieList = it
            binding.latestMovies.showMovieList()
        }

        viewModel.state.popularMovies.observe(viewLifecycleOwner) {
            popularAdapter.movieList = it
            binding.popularMovies.showMovieList()
        }

        viewModel.state.topRatedMovies.observe(viewLifecycleOwner) {
            topRatedAdapter.movieList = it
            binding.topRated.showMovieList()
        }
    }

    private fun onItemClicked(movie: ShowCaseModel) {
        viewModel.showDetails(movie)
    }

    private fun startDetails(detail: MovieDetailDomain) {
        val navDirection = ShowCaseDetailDirection(detail).createDirection()
        navigation.navigate(
            origin = this,
            destination = navDirection
        )
    }
}