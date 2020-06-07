package com.github.anastasiazhukova.fitness.utils.extensions

import com.github.anastasiazhukova.fitness.utils.constants.Constants
import java.text.SimpleDateFormat
import java.util.*

fun Long.toReadableDate(): String = SimpleDateFormat("dd LLLL yyyy", Locale.US).format(Date(this))

fun Long.toReadableTimeAndDate(): String =
    SimpleDateFormat("MMM d, yyyy h:mm a", Locale.US).format(Date(this))

fun Int.toMillisAsInt(): Int {
    return (this * Constants.Time.MILLIS_IN_SECOND).toInt()
}

fun Int.toSecondsAsInt(): Int {
    return (this / Constants.Time.MILLIS_IN_SECOND).toInt()
}

fun Int.toReadableTime(secondsLabel: String, minutesLabel: String): String {
    val seconds = this.toSecondsAsInt()

    return if (seconds < 60) {
        "$seconds $secondsLabel"
    } else {
        val minutes = "${seconds / 60} $minutesLabel"

        return if (seconds % 60 > 0) {
            "$minutes ${seconds % 60} $secondsLabel"
        } else {
            minutes
        }
    }
}