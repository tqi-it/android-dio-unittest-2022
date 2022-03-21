package io.gustavobarbosab.suspendresult

/**
 * Esta classe tem como propósito atender retornos de funções
 * suspensas do kotlin.
 */
sealed class SuspendResult<out T : Any> {

    /**
     * Como o próprio nome diz é usado em caso de sucesso na operação
     */
    data class Success<T : Any>(val data: T?) : SuspendResult<T>()

    /**
     * Deve ser emitido em casos de erros que nao estejam ligados a aplicação
     * Ex: erro de servidor, erro na base de dados, etc
     */
    data class ExternalError(val error: ExternalErrorData) : SuspendResult<Nothing>()

    /**
     * Deve ser emitido quando houver erros internos
     * Ex: Falha no parser de algum objeto, falta de internet, etc.
     */
    data class InternalError(val error: Exception) : SuspendResult<Nothing>()

    /**
     * Usado quando o erro não se enquadra nos anteriores
     */
    data class UnknownError(val error: Throwable? = null) : SuspendResult<Nothing>()

    fun <NT : Any> map(mapper: (T?) -> NT): SuspendResult<NT> {
        return when (this) {
            is Success -> Success(mapper(data))
            is ExternalError -> this
            is InternalError -> this
            is UnknownError -> this
        }
    }
}