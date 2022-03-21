package io.github.gustavobarbosab.core.network.adapter

import io.gustavobarbosab.suspendresult.SuspendResult
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import java.lang.reflect.Type

class CoroutineResponseAdapter<S : Any>(
    private val successType: Type,
    private val errorBodyConverter: Converter<ResponseBody, S>
) : CallAdapter<S, Call<SuspendResult<S>>> {

    override fun responseType(): Type = successType

    override fun adapt(call: Call<S>): Call<SuspendResult<S>> {
        return CoroutineResultCall(call, errorBodyConverter)
    }
}