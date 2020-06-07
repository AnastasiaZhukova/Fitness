package com.github.anastasiazhukova.fitness.chat.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.anastasiazhukova.fitness.chat.R
import com.github.anastasiazhukova.fitness.chat.domain.ChatModel
import com.github.anastasiazhukova.fitness.chat.domain.ChatParams
import com.github.anastasiazhukova.fitness.chat.viewmodel.ChatViewModel
import com.github.anastasiazhukova.fitness.utils.SimpleTextWatcher
import com.github.anastasiazhukova.fitness.utils.constants.Constants.String.EMPTY
import com.github.anastasiazhukova.fitness.utils.extensions.*
import kotlinx.android.synthetic.main.activity_chat.*
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.scope.viewModel

class ChatActivity : AppCompatActivity(R.layout.activity_chat) {

    val TAG = "DebugTag ChatActivity";

    private val chatViewModel: ChatViewModel by lifecycleScope.viewModel(this)
    private val chatModelObserver = object : Observer<ChatModel> {
        override fun onChanged(t: ChatModel?) {
            Log.d(TAG, "onChanged: t = $t")
            messageAdapter.items = t?.messages ?: emptyList()
        }
    }
    private lateinit var messageAdapter: ChatMessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent.getActivityExtra<ChatParams>()?.let { chatParams ->
            chatViewModel.setChatParams(chatParams)
            val chatTitle = String.format(
                resources.getString(R.string.chat_title),
                chatParams.chatOpponent
            )

            pageTitle.text = chatTitle
        }

        messageAdapter = ChatMessageAdapter(chatViewModel.getCurrentUserId())
        messages.apply {
            adapter = messageAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, true).apply {
                stackFromEnd = true
            }
        }

        sendButton.setOnClickListener {
            chatViewModel.send(textMessage.getTextAsString())
            textMessage.setText(EMPTY)
        }

        textMessage.addTextChangedListener(SimpleTextWatcher {
            if (it > 0 && !textMessage.isNullOrEmpty()) {
                sendButton.enable()
            } else {
                sendButton.disable()
            }
        })
    }

    override fun onResume() {
        super.onResume()

        chatViewModel.chatModelLiveData.observe(this, chatModelObserver)
        chatViewModel.startWatching()
    }

    companion object Companion {
        fun start(chatParams: ChatParams, context: Context) {
            context.startActivity(ChatActivity::class.java, chatParams)
        }
    }
}