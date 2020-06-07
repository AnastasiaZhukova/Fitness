package com.github.anastasiazhukova.fitness.chat.datasource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.github.anastasiazhukova.fitness.authentication.user.IUserIdHolder
import com.github.anastasiazhukova.fitness.chat.domain.ChatMessageModel
import com.github.anastasiazhukova.fitness.chat.domain.ChatModel
import com.github.anastasiazhukova.fitness.datasource.chat.ChatMessageDataModel
import com.github.anastasiazhukova.fitness.datasource.chat.IChatDao
import com.github.anastasiazhukova.fitness.utils.IMapper
import com.github.anastasiazhukova.fitness.utils.extensions.generateId

class ChatDataSource(
    private val userHolderId: IUserIdHolder,
    private val chatDao: IChatDao,
    private val messageModelDataMapper: IMapper<ChatMessageModel, ChatMessageDataModel>,
    private val messageDataModelMapper: IMapper<ChatMessageDataModel, ChatMessageModel>
) : IChatDataSource {

    val TAG = "DebugTag ChatDataSource";

    override fun subscribeToMessages(chatId: String, liveData: MutableLiveData<ChatModel>) {
        chatDao.getMessages(chatId)
            .addSnapshotListener { querySnapshot, _ ->
                val chatMessages: List<ChatMessageModel> = querySnapshot?.documents?.mapNotNull {
                    var chatMessage: ChatMessageModel? = null

                    try {
                        it.toObject(ChatMessageDataModel::class.java)?.let {
                            chatMessage = messageDataModelMapper.invoke(it)
                        }
                    } catch (e: Exception) {
                        Log.e(TAG, "subscribeToMessages: ", e)
                    }

                    chatMessage
                }?.sortedByDescending { it.messageTime } ?: emptyList()

                liveData.value = ChatModel(chatMessages)
            }
    }

    override fun sendMessage(chatId: String, message: String) {
        userHolderId.getCurrentUserId()?.let { userId ->
            val newMessage = ChatMessageModel(
                id = generateId(),
                messageSender = userId,
                messageText = message,
                messageTime = System.currentTimeMillis()
            )

            chatDao.getMessages(chatId)
                .add(messageModelDataMapper.invoke(newMessage))
        }
    }
}