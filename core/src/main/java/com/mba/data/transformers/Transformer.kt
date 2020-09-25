package com.mba.data.transformers

interface Transformer<I, O> {
    fun transform(data: I): O
}