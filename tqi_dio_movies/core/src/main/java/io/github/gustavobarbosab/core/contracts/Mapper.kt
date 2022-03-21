package io.github.gustavobarbosab.core.contracts

interface Mapper<IN, OUT> {
    fun map(input: IN): OUT
}