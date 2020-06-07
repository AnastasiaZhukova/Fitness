package com.github.anastasiazhukova.fitness.chat.usecase

import androidx.lifecycle.MutableLiveData
import com.github.anastasiazhukova.fitness.chat.datasource.IChatDataSource
import com.github.anastasiazhukova.fitness.chat.domain.ChatModel

class ChatUseCase(
    private val chatDataSource: IChatDataSource
) : IChatUseCase {

    override fun subscribeToMessages(chatId: String, data: MutableLiveData<ChatModel>) {
        chatDataSource.subscribeToMessages(chatId, data)
    }

    override fun sendMessage(chatId: String, message: String) {
        chatDataSource.sendMessage(chatId, message)
    }
}