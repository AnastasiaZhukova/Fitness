package com.github.anastasiazhukova.fitness.chat.datasource

import androidx.lifecycle.MutableLiveData
import com.github.anastasiazhukova.fitness.chat.domain.ChatModel

interface IChatDataSource {
    fun subscribeToMessages(chatId: String, liveData: MutableLiveData<ChatModel>)
    fun sendMessage(chatId: String, message: String)
}