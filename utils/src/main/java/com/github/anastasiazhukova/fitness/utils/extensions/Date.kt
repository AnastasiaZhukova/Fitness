package com.github.anastasiazhukova.fitness.utils.extensions

import com.github.anastasiazhukova.fitness.utils.constants.Constants
import java.text.SimpleDateFormat
import java.util.*

fun Long.toReadableDate(): String = SimpleDateFormat("dd LLLL yyyy", Locale.US).format(Date(this))

fun Int.toMillisAsInt(): Int {
    return (this * Constants.Time.MILLIS_IN_SECOND).toInt()
}

fun Int.toSecondsAsInt(): Int {
    return (this / Constants.Time.MILLIS_IN_SECOND).toInt()
}