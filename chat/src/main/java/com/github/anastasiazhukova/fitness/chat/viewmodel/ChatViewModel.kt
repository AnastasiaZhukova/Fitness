package com.github.anastasiazhukova.fitness.chat.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.anastasiazhukova.fitness.authentication.user.IUserIdHolder
import com.github.anastasiazhukova.fitness.chat.datasource.IChatDataSource
import com.github.anastasiazhukova.fitness.chat.domain.ChatModel
import com.github.anastasiazhukova.fitness.chat.domain.ChatParams
import com.github.anastasiazhukova.fitness.utils.constants.Constants.String.EMPTY
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class ChatViewModel(
    private val userIdHolder: IUserIdHolder,
    private val chatUseCase: IChatDataSource
) : ViewModel() {

    val TAG = "DebugTag ChatViewModel";

    private val coroutineScopeJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + coroutineScopeJob)

    private val _chatModelLiveData = MutableLiveData<ChatModel>()

    private var chatParams: ChatParams? = null

    val chatModelLiveData: LiveData<ChatModel>
        get() = _chatModelLiveData

    fun setChatParams(chatParams: ChatParams) {
        this.chatParams = chatParams
    }

    fun startWatching() {
        chatParams?.let {
            chatUseCase.subscribeToMessages(it.chatId, _chatModelLiveData)
        }
    }

    fun send(message: String) {
        chatParams?.let {
            chatUseCase.sendMessage(it.chatId, message)
        }
    }

    fun getCurrentUserId() = userIdHolder.getCurrentUserId() ?: EMPTY

    override fun onCleared() {
        super.onCleared()

        coroutineScopeJob.cancel()
    }
}