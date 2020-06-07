package com.github.anastasiazhukova.fitness.chat.usecase

import androidx.lifecycle.MutableLiveData
import com.github.anastasiazhukova.fitness.chat.domain.ChatModel

interface IChatUseCase {
    fun subscribeToMessages(chatId: String, data: MutableLiveData<ChatModel>)
    fun sendMessage(chatId: String, message: String)
}