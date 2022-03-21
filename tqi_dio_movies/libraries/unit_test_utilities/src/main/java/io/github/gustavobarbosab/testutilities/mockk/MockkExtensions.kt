package io.github.gustavobarbosab.testutilities.mockk

import io.mockk.MockKVerificationScope
import io.mockk.verify

fun verifyNever(verifyBlock: MockKVerificationScope.() -> Unit) =
    verify(exactly = 0, verifyBlock = verifyBlock)

fun verifyOnce(verifyBlock: MockKVerificationScope.() -> Unit) =
    verify(exactly = 1, verifyBlock = verifyBlock)