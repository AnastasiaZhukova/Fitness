package com.github.anastasiazhukova.fitness.chat.presentation

import com.github.anastasiazhukova.fitness.chat.domain.ChatModel

sealed class ChatScreenUiState {
    object Loading : ChatScreenUiState()
    class Success(val model: ChatModel?) : ChatScreenUiState()
    class Error(val e: Throwable) : ChatScreenUiState()
}