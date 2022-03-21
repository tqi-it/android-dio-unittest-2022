package io.gustavobarbosab.suspendresult

interface CoroutineResultHandler {

    fun <T : Any> handleResult(
        result: SuspendResult<T>,
        onSuccess: (T?) -> Unit,
        onError: () -> Unit
    ) {
        when (result) {
            is SuspendResult.Success -> onSuccess(result.data)
            else -> onError()
        }
    }

    fun <T : Any, U> handleResult(
        result: SuspendResult<T>,
        onSuccess: (T?) -> Unit,
        onExternalError: (ExternalErrorData) -> Unit,
        onError: () -> Unit,
    ) {
        when (result) {
            is SuspendResult.Success -> onSuccess(result.data)
            is SuspendResult.ExternalError -> onExternalError(result.error)
            else -> onError()
        }
    }
}