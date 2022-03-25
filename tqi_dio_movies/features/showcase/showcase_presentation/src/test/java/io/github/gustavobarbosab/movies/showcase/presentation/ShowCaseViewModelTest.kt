package io.github.gustavobarbosab.movies.showcase.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.github.gustavobarbosab.detail.model.MovieDetailDomain
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




}