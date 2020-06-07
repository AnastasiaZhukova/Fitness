package com.github.anastasiazhukova.fitness.chat.domain

import com.github.anastasiazhukova.fitness.datasource.chat.ChatMessageDataModel
import com.github.anastasiazhukova.fitness.utils.IMapper

class ChatMessageModelDataMapper : IMapper<ChatMessageModel, ChatMessageDataModel> {

    override fun invoke(messageModel: ChatMessageModel): ChatMessageDataModel {
        return ChatMessageDataModel(
            id = messageModel.id,
            messageSender = messageModel.messageSender,
            messageText = messageModel.messageText,
            messageTime = messageModel.messageTime
        )
    }
}