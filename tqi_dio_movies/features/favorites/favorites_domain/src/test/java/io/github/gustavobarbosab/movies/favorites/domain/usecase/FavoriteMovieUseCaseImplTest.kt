package io.github.gustavobarbosab.movies.favorites.domain.usecase

import io.github.gustavobarbosab.movies.favorites.domain.FavoriteMovieMocker
import io.github.gustavobarbosab.movies.favorites.domain.model.MovieState
import io.github.gustavobarbosab.movies.favorites.domain.repository.FavoriteMovieRepository
import io.github.gustavobarbosab.testutilities.coroutines.CoroutineDispatcherRule
import io.gustavobarbosab.suspendresult.SuspendResult
import io.gustavobarbosab.suspendresult.SuspendResult.Success
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Rule
import org.junit.Test

class FavoriteMovieUseCaseImplTest {

    @get:Rule
    val dispatcherRule = CoroutineDispatcherRule()

    private val repository = mockk<FavoriteMovieRepository>(relaxed = true)
    private val useCase: FavoriteMovieUseCase = FavoriteMovieUseCaseImpl(repository)

    @Test
    fun `when I can change movie state to unlike`() = dispatcherRule.runBlockingTest {
        // GIVEN
        val movie = FavoriteMovieMocker.createMovie()
        coEvery { repository.isMovieFavorite(any()) } returns Success(true)
        coEvery { repository.unlikeMovie(movie.id) } returns Success(Unit)

        // WHEN
        val result = useCase.updateFavoriteMovie(movie)

        // THEN
        assertThat(result, instanceOf(Success::class.java))
        assertEquals(MovieState.MovieUnliked, (result as Success).data)
    }

    @Test
    fun `when I can change movie state to like`() = dispatcherRule.runBlockingTest {
        // GIVEN
        val movie = FavoriteMovieMocker.createMovie()
        coEvery { repository.isMovieFavorite(any()) } returns Success(false)
        coEvery { repository.likeMovie(movie) } returns Success(Unit)

        // WHEN
        val result = useCase.updateFavoriteMovie(movie)

        // THEN
        assertThat(result, instanceOf(Success::class.java))
        assertEquals(MovieState.MovieLiked, (result as Success).data)
    }

    @Test
    fun `when I can change movie state and occur an exception`() = dispatcherRule.runBlockingTest {
        // GIVEN
        val movie = FavoriteMovieMocker.createMovie()
        coEvery { repository.isMovieFavorite(any()) } returns SuspendResult.UnknownError()

        // WHEN
        val result = useCase.updateFavoriteMovie(movie)

        // THEN
        assertThat(result, instanceOf(SuspendResult.UnknownError::class.java))
    }
}