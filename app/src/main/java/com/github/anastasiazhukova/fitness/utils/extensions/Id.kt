package com.github.anastasiazhukova.fitness.utils.extensions

import java.util.*

fun generateId(): String {
    return UUID.randomUUID().toString() + System.currentTimeMillis().toString()
}