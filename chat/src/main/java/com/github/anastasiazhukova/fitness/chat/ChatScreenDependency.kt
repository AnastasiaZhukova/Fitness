package com.github.anastasiazhukova.fitness.chat

import com.github.anastasiazhukova.fitness.chat.datasource.ChatDataSource
import com.github.anastasiazhukova.fitness.chat.datasource.IChatDataSource
import com.github.anastasiazhukova.fitness.chat.domain.ChatMessageDataModelMapper
import com.github.anastasiazhukova.fitness.chat.domain.ChatMessageModelDataMapper
import com.github.anastasiazhukova.fitness.chat.presentation.ChatActivity
import com.github.anastasiazhukova.fitness.chat.usecase.ChatUseCase
import com.github.anastasiazhukova.fitness.chat.usecase.IChatUseCase
import com.github.anastasiazhukova.fitness.chat.viewmodel.ChatViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ChatScreenDependency {
    val module = module {
        scope<ChatActivity> {
            scoped<IChatDataSource> {
                ChatDataSource(
                    userHolderId = get(),
                    chatDao = get(),
                    messageModelDataMapper = ChatMessageModelDataMapper(),
                    messageDataModelMapper = ChatMessageDataModelMapper()
                )
            }

            scoped<IChatUseCase> {
                ChatUseCase(
                    chatDataSource = get()
                )
            }

            viewModel {
                ChatViewModel(
                    userIdHolder = get(),
                    chatUseCase = get()
                )
            }
        }
    }
}