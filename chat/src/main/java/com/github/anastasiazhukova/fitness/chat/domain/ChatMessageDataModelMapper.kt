package com.github.anastasiazhukova.fitness.chat.domain

import com.github.anastasiazhukova.fitness.datasource.chat.ChatMessageDataModel
import com.github.anastasiazhukova.fitness.utils.IMapper

class ChatMessageDataModelMapper : IMapper<ChatMessageDataModel, ChatMessageModel> {

    override fun invoke(messageModel: ChatMessageDataModel): ChatMessageModel {
        return ChatMessageModel(
            id = messageModel.id,
            messageSender = messageModel.messageSender,
            messageText = messageModel.messageText,
            messageTime = messageModel.messageTime
        )
    }
}