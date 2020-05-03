package com.github.anastasiazhukova.fitness.utils.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Long.toReadableDate(): String = SimpleDateFormat("dd LLLL yyyy", Locale.US).format(Date(this))