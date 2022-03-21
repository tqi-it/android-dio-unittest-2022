package io.github.gustavobarbosab.movies.favorite.presentation

import android.os.Bundle
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import io.github.gustavobarbosab.commons.ui.base.BaseFragment
import io.github.gustavobarbosab.commons.widget.snackbar.SnackBarType
import io.github.gustavobarbosab.commons.widget.snackbar.showSnackBar
import io.github.gustavobarbosab.movies.favorite.model.FavoriteModel
import io.github.gustavobarbosab.movies.favorite.presentation.FavoritesMoviesState.LayoutState
import io.github.gustavobarbosab.movies.favorite.presentation.FavoritesMoviesState.ViewAction
import io.github.gustavobarbosab.movies.extension.applicationToolbar
import io.github.gustavobarbosab.movies.extension.requireAppComponent
import io.github.gustavobarbosab.movies.favorite.R
import io.github.gustavobarbosab.movies.favorite.databinding.FragmentFavoriteMoviesBinding
import io.github.gustavobarbosab.movies.favorite.di.DaggerFavoritesComponent
import javax.inject.Inject

class FavoriteMoviesFragment : BaseFragment<FragmentFavoriteMoviesBinding>() {

    @Inject
    lateinit var viewModelFactory: FavoritesMoviesViewModelFactory
    private lateinit var viewModel: FavoritesMoviesViewModel
    private val adapter = FavoritesMoviesRecyclerAdapter(this::unlikeMovie)

    override val layoutId: Int = R.layout.fragment_favorite_movies

    override fun initializeViews(savedInstance: Bundle?) {
        DaggerFavoritesComponent.factory()
            .create(requireAppComponent())
            .inject(this)

        createViewModel()
        setupToolbar()
        observeState()
        binding.favoriteMoviesRecyclerView.adapter = adapter
        binding.favoritesTryAgain.buttonListener { viewModel.fetchFavorites() }
        viewModel.fetchFavorites()
    }

    private fun createViewModel() {
        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(FavoritesMoviesViewModel::class.java)
    }

    private fun setupToolbar() = applicationToolbar {
        title = getString(R.string.favorites_toolbar_title)
    }

    private fun unlikeMovie(position: Int, favoriteModel: FavoriteModel) {
        viewModel.unlikeMovie(favoriteModel, position)
    }

    private fun observeState() = with(viewModel.state) {
        layout.observe(viewLifecycleOwner) {
            when (it) {
                LayoutState.HideAll -> hideAllViews()
                LayoutState.ShowEmptyState -> showEmptyState()
                is LayoutState.ShowRecyclerView -> showRecyclerView(it.list)
                LayoutState.ShowTryAgain -> showTryAgain()
            }
        }

        actions.observe(viewLifecycleOwner) {
            when (it) {
                ViewAction.HideLoading -> hideLoading()
                ViewAction.ShowLoading -> showLoading()
                ViewAction.MovieUnliked -> movieUnlikedSuccess()
                ViewAction.UnlikeMovieFailure -> unlikeMovieFailure()
                is ViewAction.RemoveUnlikedMovie -> adapter.removeMovie(it.position)
            }
        }
    }

    private fun movieUnlikedSuccess() {
        requireActivity().showSnackBar(
            SnackBarType.Success,
            getString(R.string.favorites_movies_unlike_success)
        )
    }

    private fun unlikeMovieFailure() {
        requireActivity().showSnackBar(
            SnackBarType.Error,
            getString(R.string.favorites_movies_unlike_error)
        )
    }

    private fun hideAllViews() {
        binding.favoritesTryAgain.isGone = true
        binding.favoriteMoviesRecyclerView.isGone = true
        binding.favoritesEmptyState.isGone = true
    }

    private fun showTryAgain() {
        binding.favoritesTryAgain.isVisible = true
        binding.favoriteMoviesRecyclerView.isGone = true
        binding.favoritesEmptyState.isGone = true
    }

    private fun showEmptyState() {
        binding.favoritesEmptyState.isVisible = true
        binding.favoritesTryAgain.isGone = true
        binding.favoriteMoviesRecyclerView.isGone = true
    }

    private fun showRecyclerView(list: MutableList<FavoriteModel>) {
        adapter.list = list
        binding.favoriteMoviesRecyclerView.isVisible = true
        binding.favoritesEmptyState.isGone = true
        binding.favoritesTryAgain.isGone = true
    }
}