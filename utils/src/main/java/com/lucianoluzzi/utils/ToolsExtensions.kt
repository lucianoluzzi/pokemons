package com.lucianoluzzi.utils

val <T> T.exhaustive: T
    get() = this

val Any.doNothing: Unit
    get() = Unit