package io.github.gustavobarbosab.movies.detail.presentation

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import io.github.gustavobarbosab.commons.ui.base.BaseFragment
import io.github.gustavobarbosab.commons.ui.extension.loadImage
import io.github.gustavobarbosab.commons.widget.snackbar.SnackBarType
import io.github.gustavobarbosab.commons.widget.snackbar.showSnackBar
import io.github.gustavobarbosab.commons.widget.toolbar.buttons.BackButtonType
import io.github.gustavobarbosab.movies.detail.R
import io.github.gustavobarbosab.movies.detail.databinding.FragmentMovieDetailBinding
import io.github.gustavobarbosab.movies.detail.di.DaggerDetailComponent
import io.github.gustavobarbosab.movies.detail.model.DetailsButtonType
import io.github.gustavobarbosab.movies.detail.presentation.DetailMovieState.ViewAction
import io.github.gustavobarbosab.movies.extension.applicationToolbar
import io.github.gustavobarbosab.movies.extension.requireAppComponent
import javax.inject.Inject

class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding>() {

    override val layoutId: Int = R.layout.fragment_movie_detail

    private val args: MovieDetailFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: MovieDetailViewModelFactory

    private lateinit var viewModel: MovieDetailViewModel

    override fun initializeViews(savedInstance: Bundle?) {
        DaggerDetailComponent
            .factory()
            .create(requireAppComponent())
            .inject(this)

        setupViewModel()
        setupToolbar()
        observeStates()
        binding.movieFab.setOnClickListener { viewModel.favoriteMovie() }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieDetailViewModel::class.java)
        viewModel.init(args.detailModel)
    }

    private fun setupToolbar() = applicationToolbar {
        title = getString(R.string.detail_toolbar_title)
        backButtonType = BackButtonType.ARROW
    }

    private fun observeStates() = with(viewModel.state) {
        movie.observe(viewLifecycleOwner) { movieDetail ->
            binding.moviePoster.loadImage(movieDetail.poster)
            binding.movieName.text = movieDetail.title
            binding.movieDescription.text = movieDetail.description
        }

        favoriteButtonState.observe(viewLifecycleOwner) {
            val newImage = when (it) {
                DetailsButtonType.Filled -> io.github.gustavobarbosab.commons.R.drawable.ic_heart_filled
                DetailsButtonType.Outline -> io.github.gustavobarbosab.commons.R.drawable.ic_heart_outline
            }
            binding.movieFab.setImageResource(newImage)
        }

        actions.observe(viewLifecycleOwner) {
            when (it) {
                ViewAction.HideLoading -> hideLoading()
                ViewAction.ShowLoading -> showLoading()
                ViewAction.StartScreenFailure -> showSnackBar(
                    SnackBarType.Error,
                    R.string.detail_start_screen_failure
                )
                ViewAction.FavoriteMovieFailure -> showSnackBar(
                    SnackBarType.Error,
                    R.string.detail_load_favorites_failure
                )
                ViewAction.MovieUnliked -> showSnackBar(
                    SnackBarType.Success,
                    R.string.detail_movie_unliked
                )
                ViewAction.MovieLiked -> showSnackBar(
                    SnackBarType.Success,
                    R.string.detail_movie_added
                )
            }
        }
    }

    private fun showSnackBar(type: SnackBarType, message: Int) {
        requireActivity().showSnackBar(
            type,
            getString(message)
        )
    }
}