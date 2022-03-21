package io.github.gustavobarbosab.movies.detail.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.github.gustavobarbosab.movies.detail.MovieDetailMocker
import io.github.gustavobarbosab.movies.detail.model.DetailModel
import io.github.gustavobarbosab.movies.detail.model.DetailsButtonType
import io.github.gustavobarbosab.movies.favorites.domain.model.MovieState
import io.github.gustavobarbosab.movies.favorites.domain.usecase.FavoriteMovieUseCase
import io.github.gustavobarbosab.testutilities.coroutines.CoroutineDispatcherRule
import io.gustavobarbosab.suspendresult.SuspendResult
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

class MovieDetailViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val dispatcherRule = CoroutineDispatcherRule()

    private val useCase = mockk<FavoriteMovieUseCase>()
    private val movieObserver: Observer<DetailModel> = mockk(relaxed = true)
    private val buttonTypeObserver: Observer<DetailsButtonType> = mockk(relaxed = true)
    private val detailActions: Observer<DetailMovieState.ViewAction> = mockk(relaxed = true)
    private val viewModel = MovieDetailViewModel(useCase)

    @Test
    fun `when start the screen and like movie`() = dispatcherRule.runBlockingTest {
        // GIVEN
        val detailArgument = MovieDetailMocker.createMovieDetailArgument()

        coEvery {
            useCase.isMovieFavorite(MovieDetailMocker.MOVIE_ID)
        } returns SuspendResult.Success(MovieState.MovieUnliked)

        coEvery {
            useCase.updateFavoriteMovie(any())
        } returns SuspendResult.Success(MovieState.MovieLiked)

        viewModel.state.movie.observeForever(movieObserver)
        viewModel.state.actions.observeForever(detailActions)
        viewModel.state.favoriteButtonState.observeForever(buttonTypeObserver)

        // WHEN
        viewModel.init(detailArgument)
        viewModel.favoriteMovie()

        // THEN
        verify {
            detailActions.onChanged(DetailMovieState.ViewAction.ShowLoading)
            buttonTypeObserver.onChanged(DetailsButtonType.Filled)
            detailActions.onChanged(DetailMovieState.ViewAction.MovieLiked)
            detailActions.onChanged(DetailMovieState.ViewAction.HideLoading)
        }
    }

    @Test
    fun `when start the screen and unlike movie`() = dispatcherRule.runBlockingTest {
        // GIVEN
        val detailArgument = MovieDetailMocker.createMovieDetailArgument()

        coEvery {
            useCase.isMovieFavorite(MovieDetailMocker.MOVIE_ID)
        } returns SuspendResult.Success(MovieState.MovieLiked)

        coEvery {
            useCase.updateFavoriteMovie(any())
        } returns SuspendResult.Success(MovieState.MovieUnliked)

        viewModel.state.movie.observeForever(movieObserver)
        viewModel.state.actions.observeForever(detailActions)
        viewModel.state.favoriteButtonState.observeForever(buttonTypeObserver)

        // WHEN
        viewModel.init(detailArgument)
        viewModel.favoriteMovie()

        // THEN
        verify {
            detailActions.onChanged(DetailMovieState.ViewAction.ShowLoading)
            buttonTypeObserver.onChanged(DetailsButtonType.Outline)
            detailActions.onChanged(DetailMovieState.ViewAction.MovieUnliked)
            detailActions.onChanged(DetailMovieState.ViewAction.HideLoading)
        }
    }
}


