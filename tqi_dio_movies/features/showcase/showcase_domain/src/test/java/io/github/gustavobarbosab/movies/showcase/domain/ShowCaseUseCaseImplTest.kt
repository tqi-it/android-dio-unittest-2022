package io.github.gustavobarbosab.movies.showcase.domain

import io.github.gustavobarbosab.showcase.repository.ShowCaseRepository
import io.github.gustavobarbosab.showcase.usecase.ShowCaseUseCaseImpl
import io.github.gustavobarbosab.testutilities.coroutines.CoroutineDispatcherRule
import io.gustavobarbosab.suspendresult.SuspendResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test

class ShowCaseUseCaseImplTest {

    @get:Rule
    val dispatcherRule = CoroutineDispatcherRule()

    val movieRepository = mockk<ShowCaseRepository>()
    val useCase = ShowCaseUseCaseImpl(movieRepository)

    @Test
    fun `when I can get latest movies`() = dispatcherRule.runBlockingTest {
        // GIVEN
        coEvery { movieRepository.getLatestMovies() } returns SuspendResult.Success(mockk())

        // WHEN
        useCase.getLatestMovies()

        // THEN
        coVerify {
            movieRepository.getLatestMovies()
        }
    }

    @Test
    fun `when I can get playing now movies`() = dispatcherRule.runBlockingTest {
        // GIVEN
        coEvery { movieRepository.getPlayingNow() } returns SuspendResult.Success(mockk())

        // WHEN
        useCase.getPlayingNow()

        // THEN
        coVerify {
            movieRepository.getPlayingNow()
        }
    }

    @Test
    fun `when I can get popular movies`() = dispatcherRule.runBlockingTest {
        // GIVEN
        coEvery { movieRepository.getPopularMovies() } returns SuspendResult.Success(mockk())

        // WHEN
        useCase.getPopularMovies()

        // THEN
        coVerify {
            movieRepository.getPopularMovies()
        }
    }

    @Test
    fun `when I can get top rated movies`() = dispatcherRule.runBlockingTest {
        // GIVEN
        coEvery { movieRepository.getTopRatedMovies() } returns SuspendResult.Success(mockk())

        // WHEN
        useCase.getTopRatedMovies()

        // THEN
        coVerify {
            movieRepository.getTopRatedMovies()
        }
    }
}