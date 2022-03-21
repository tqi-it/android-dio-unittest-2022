package io.github.gustavobarbosab.movies.showcase.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.github.gustavobarbosab.movies.showcase.presentation.model.ShowCaseModel
import io.github.gustavobarbosab.showcase.model.MovieShowCase
import io.github.gustavobarbosab.showcase.usecase.ShowCaseUseCase
import io.github.gustavobarbosab.testutilities.coroutines.CoroutineDispatcherRule
import io.github.gustavobarbosab.testutilities.mockk.verifyNever
import io.github.gustavobarbosab.testutilities.mockk.verifyOnce
import io.gustavobarbosab.suspendresult.SuspendResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

class ShowCaseViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val dispatcherRule = CoroutineDispatcherRule()

    private val useCase = mockk<ShowCaseUseCase>()
    private val stateObserver: Observer<ShowCaseViewState.ViewAction> = mockk(relaxed = true)
    private val listObserver: Observer<List<ShowCaseModel>> = mockk(relaxed = true)
    private val viewModel = ShowCaseViewModel(useCase)

    @Test
    fun `when I can get latest movies and had success`() = dispatcherRule.runBlockingTest {
        // GIVEN
        val expectedResult = SuspendResult.Success(emptyList<MovieShowCase>())
        coEvery { useCase.getLatestMovies() } returns expectedResult
        viewModel.state.action.observeForever(stateObserver)
        viewModel.state.latestMovies.observeForever(listObserver)

        // WHEN
        viewModel.getLatestMovies()

        // THEN
        verifyOnce {
            stateObserver.onChanged(ShowCaseViewState.ViewAction.ShowLatestLoading)
            listObserver.onChanged(any())
        }
    }

    @Test
    fun `when I can get latest movies and had error`() = dispatcherRule.runBlockingTest {
        // GIVEN
        val expectedResult = SuspendResult.UnknownError(Exception())
        coEvery { useCase.getLatestMovies() } returns expectedResult
        viewModel.state.action.observeForever(stateObserver)
        viewModel.state.latestMovies.observeForever(listObserver)

        // WHEN
        viewModel.getLatestMovies()

        // THEN
        verifyOnce {
            stateObserver.onChanged(ShowCaseViewState.ViewAction.ShowLatestLoading)
            stateObserver.onChanged(ShowCaseViewState.ViewAction.ErrorLoadLatest)
        }

        verifyNever {
            listObserver.onChanged(any())
        }
    }

    @Test
    fun `when I can get top rated movies and had success`() = dispatcherRule.runBlockingTest {
        // GIVEN
        val expectedResult = SuspendResult.Success(emptyList<MovieShowCase>())
        coEvery { useCase.getTopRatedMovies() } returns expectedResult
        viewModel.state.action.observeForever(stateObserver)
        viewModel.state.topRatedMovies.observeForever(listObserver)

        // WHEN
        viewModel.getTopRatedMovies()

        // THEN
        verifyOnce {
            stateObserver.onChanged(ShowCaseViewState.ViewAction.ShowTopRatedLoading)
            listObserver.onChanged(any())
        }
    }

    @Test
    fun `when I can get top rated movies and had error`() = dispatcherRule.runBlockingTest {
        // GIVEN
        val expectedResult = SuspendResult.UnknownError(Exception())
        coEvery { useCase.getTopRatedMovies() } returns expectedResult
        viewModel.state.action.observeForever(stateObserver)
        viewModel.state.topRatedMovies.observeForever(listObserver)

        // WHEN
        viewModel.getTopRatedMovies()

        // THEN
        verifyOnce {
            stateObserver.onChanged(ShowCaseViewState.ViewAction.ShowTopRatedLoading)
            stateObserver.onChanged(ShowCaseViewState.ViewAction.ErrorLoadTopRated)
        }

        verifyNever {
            listObserver.onChanged(any())
        }
    }
}