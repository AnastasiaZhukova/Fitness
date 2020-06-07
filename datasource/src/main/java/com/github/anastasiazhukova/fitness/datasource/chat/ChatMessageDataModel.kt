package com.github.anastasiazhukova.fitness.datasource.chat

import com.github.anastasiazhukova.fitness.datasource.chat.ChatDaoConstants.ID
import com.github.anastasiazhukova.fitness.datasource.chat.ChatDaoConstants.MESSAGE_SENDER
import com.github.anastasiazhukova.fitness.datasource.chat.ChatDaoConstants.MESSAGE_TEXT
import com.github.anastasiazhukova.fitness.datasource.chat.ChatDaoConstants.MESSAGE_TIME
import com.github.anastasiazhukova.fitness.utils.constants.Constants.String.EMPTY
import com.google.gson.annotations.SerializedName

data class ChatMessageDataModel(
    @SerializedName(ID)
    val id: String = EMPTY,

    @SerializedName(MESSAGE_SENDER)
    val messageSender: String = EMPTY,

    @SerializedName(MESSAGE_TEXT)
    val messageText: String = EMPTY,

    @SerializedName(MESSAGE_TIME)
    val messageTime: Long = -1
)