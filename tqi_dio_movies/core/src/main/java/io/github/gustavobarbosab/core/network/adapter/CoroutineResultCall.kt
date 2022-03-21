package io.github.gustavobarbosab.core.network.adapter

import io.gustavobarbosab.suspendresult.SuspendResult
import io.gustavobarbosab.suspendresult.ExternalErrorData
import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException

internal class CoroutineResultCall<S : Any>(
    private val delegate: Call<S>,
    private val errorConverter: Converter<ResponseBody, S>
) : Call<SuspendResult<S>> {

    // TODO refatorar essa classe para melhorar a legibilidade e separamento de responsabilidades
    override fun enqueue(callback: Callback<SuspendResult<S>>) {
        return delegate.enqueue(object : Callback<S> {
            override fun onResponse(call: Call<S>, response: Response<S>) {
                val body = response.body()
                val code = response.code()
                val error = response.errorBody()

                if (response.isSuccessful) {
                    if (body != null) {
                        callback.onResponse(
                            this@CoroutineResultCall,
                            Response.success(SuspendResult.Success(body))
                        )
                    } else {
                        // Response is successful but the body is null
                        callback.onResponse(
                            this@CoroutineResultCall,
                            Response.success(SuspendResult.Success(null))
                        )
                    }
                } else {
                    val errorBody = when {
                        error == null -> null
                        error.contentLength() == 0L -> null
                        else -> try {
                            errorConverter.convert(error)
                        } catch (ex: Exception) {
                            null
                        }
                    }
                    if (errorBody != null) {
                        val errorMessage = getErrorMessage(errorBody.toString())
                        val error = ExternalErrorData(code, errorMessage)
                        callback.onResponse(
                            this@CoroutineResultCall,
                            Response.success(SuspendResult.ExternalError(error))
                        )
                    } else {
                        callback.onResponse(
                            this@CoroutineResultCall,
                            Response.success(SuspendResult.UnknownError(null))
                        )
                    }
                }
            }

            override fun onFailure(call: Call<S>, throwable: Throwable) {
                val networkResponse = when (throwable) {
                    is IOException -> SuspendResult.InternalError(throwable)
                    else -> SuspendResult.UnknownError(throwable)
                }
                callback.onResponse(this@CoroutineResultCall, Response.success(networkResponse))
            }
        })
    }

    override fun isExecuted() = delegate.isExecuted

    override fun clone() = CoroutineResultCall(delegate.clone(), errorConverter)

    override fun isCanceled() = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun execute(): Response<SuspendResult<S>> {
        throw UnsupportedOperationException("SuspendResult doesn't support execute")
    }

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()

    private fun getErrorMessage(body: String): String = try {
        JSONObject(body).getString("message_value").orEmpty()
    } catch (e: Exception) {
        //TODO get default message
        ""
    }
}