package com.github.anastasiazhukova.fitness.chat.domain

data class ChatMessageModel(
    val id: String,
    val messageSender: String,
    val messageText: String,
    val messageTime: Long
)