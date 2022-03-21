package io.github.gustavobarbosab.movies.favorite.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.github.gustavobarbosab.movies.favorite.presentation.FavoritesMoviesState.LayoutState
import io.github.gustavobarbosab.movies.favorite.presentation.FavoritesMoviesState.ViewAction
import io.github.gustavobarbosab.movies.favorites.domain.model.MovieFavorite
import io.github.gustavobarbosab.movies.favorites.domain.usecase.FavoriteMovieUseCase
import io.github.gustavobarbosab.testutilities.coroutines.CoroutineDispatcherRule
import io.gustavobarbosab.suspendresult.SuspendResult
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Assert.assertThat
import org.junit.Rule
import org.junit.Test

class FavoritesMoviesViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val dispatcherRule = CoroutineDispatcherRule()

    private val useCase = mockk<FavoriteMovieUseCase>()
    private val actionObserver: Observer<ViewAction> = mockk(relaxed = true)
    private val stateObserver: Observer<LayoutState> = mockk(relaxed = true)
    private val viewModel = FavoritesMoviesViewModel(useCase)

    @Test
    fun `when I start the screen and fetch favorites and has movies saved`() =
        dispatcherRule.runBlockingTest {
            // GIVEN
            val mockedReturn = MovieFavorite(
                2,
                "",
                "",
                "",
                "",
                ""
            )
            val expectedResult = SuspendResult.Success(listOf(mockedReturn))
            coEvery { useCase.fetchFavorites() } returns expectedResult
            viewModel.state.actions.observeForever(actionObserver)
            viewModel.state.layout.observeForever(stateObserver)

            // WHEN
            viewModel.fetchFavorites()

            // THEN
            verify {
                actionObserver.onChanged(ViewAction.ShowLoading)
                stateObserver.onChanged(LayoutState.HideAll)
                actionObserver.onChanged(ViewAction.HideLoading)
            }
            assertThat(
                viewModel.state.layout.value,
                instanceOf(LayoutState.ShowRecyclerView::class.java)
            )
        }

    @Test
    fun `when I start the screen and fetch favorites and has not movies saved`() =
        dispatcherRule.runBlockingTest {
            // GIVEN
            val expectedResult = SuspendResult.Success(emptyList<MovieFavorite>())
            coEvery { useCase.fetchFavorites() } returns expectedResult
            viewModel.state.actions.observeForever(actionObserver)
            viewModel.state.layout.observeForever(stateObserver)

            // WHEN
            viewModel.fetchFavorites()

            // THEN
            verify {
                actionObserver.onChanged(ViewAction.ShowLoading)
                stateObserver.onChanged(LayoutState.HideAll)
                stateObserver.onChanged(LayoutState.ShowEmptyState)
                actionObserver.onChanged(ViewAction.HideLoading)
            }
        }

    @Test
    fun `when I start the screen and fetch favorites and has error`() =
        dispatcherRule.runBlockingTest {
            // GIVEN
            val expectedResult = SuspendResult.UnknownError()
            coEvery { useCase.fetchFavorites() } returns expectedResult
            viewModel.state.actions.observeForever(actionObserver)
            viewModel.state.layout.observeForever(stateObserver)

            // WHEN
            viewModel.fetchFavorites()

            // THEN
            verify {
                actionObserver.onChanged(ViewAction.ShowLoading)
                stateObserver.onChanged(LayoutState.HideAll)
                stateObserver.onChanged(LayoutState.ShowTryAgain)
                actionObserver.onChanged(ViewAction.HideLoading)
            }
        }

}