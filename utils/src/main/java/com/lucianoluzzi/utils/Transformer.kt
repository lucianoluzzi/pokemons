package com.lucianoluzzi.utils

interface Transformer<I, O> {
    @Throws(Exception::class)
    fun transform(input: I): O
}
