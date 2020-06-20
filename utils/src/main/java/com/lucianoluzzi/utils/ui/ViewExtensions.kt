package com.lucianoluzzi.utils.ui

import android.view.View

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide(keepSize: Boolean = false) {
    if (keepSize)
        visibility = View.INVISIBLE
    else
        visibility = View.GONE
}