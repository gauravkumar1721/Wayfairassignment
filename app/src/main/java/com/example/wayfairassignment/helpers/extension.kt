package com.example.wayfairassignment.helpers

fun Double.roundToInt(): Int {
    return if (this >= 0) {
        (this + 0.5).toInt()
    } else {
        (this - 0.5).toInt()
    }
}