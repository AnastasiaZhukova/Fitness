package com.github.anastasiazhukova.fitness.chat.utils

fun getChatId(trainerId: String, userId: String): String {
    return "${trainerId}_${userId}"
}